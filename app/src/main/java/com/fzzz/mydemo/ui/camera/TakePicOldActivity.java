package com.fzzz.mydemo.ui.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Base64;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Shen Chao
 * Created by 2019-01-15
 */
@Route(path = Constants.PATH_APP_TAKEPIC_OLD)
public class TakePicOldActivity extends BaseActivity implements SurfaceHolder.Callback {
    private SurfaceView mSurfaceView;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private ImageView imageView;
    private RelativeLayout cameraRl;
    private RelativeLayout showPicRl;
    private TextView cancel;
    private TextView confirm;
    private ImageView imageOcr, imageFace;
    private int degrees;
    private boolean frontCamera;
    private boolean photoCardFront;
    private int cameraID;
    private String param;
    private Bitmap bitmap;

    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            cameraRl.setVisibility(View.GONE);
            showPicRl.setVisibility(View.VISIBLE);

            bitmap = toRotating(data, degrees);
            imageView.setImageBitmap(bitmap);

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_camera_old;
    }

    @Override
    public void beforeSetContentView() {
        super.beforeSetContentView();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //去掉状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSurfaceView = findViewById(R.id.surfaceview);
        imageView = findViewById(R.id.image);
        cameraRl = findViewById(R.id.rl_camera);
        showPicRl = findViewById(R.id.show_pic);
        cancel = findViewById(R.id.clear);
        confirm = findViewById(R.id.done);
        imageOcr = findViewById(R.id.image_ocr);
        imageFace = findViewById(R.id.image_face);

        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);

        initData();
        initView();
        initEvent();

    }

    private void initData() {
        frontCamera = getIntent().getBooleanExtra("frontCamera", false);
        photoCardFront = getIntent().getBooleanExtra("photoCardFront", true);
        param = getIntent().getStringExtra("param");
    }

    private void initView() {
        if (frontCamera) {
            imageFace.setVisibility(View.VISIBLE);
            imageOcr.setVisibility(View.GONE);
            cameraID = Constants.CAMERA_FACE;
        } else {
            if (!photoCardFront) {
                imageOcr.setImageResource(R.mipmap.ocr_back);
            }
            imageFace.setVisibility(View.GONE);
            imageOcr.setVisibility(View.VISIBLE);
            cameraID = Constants.CAMERA_OCR;
        }

    }

    private void initEvent() {
        mSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.autoFocus(null);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraRl.setVisibility(View.VISIBLE);
                showPicRl.setVisibility(View.GONE);
                mCamera.stopPreview();
                startPreview(mCamera, mHolder);
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = getBase64ByBitmap(bitmap);
                CameraActivity.base64Result = result;

                setResult(RESULT_OK);
                finish();
            }
        });
    }

    /**
     * 拍照
     *
     * @param view
     */
    public void takePic(View view) {
        switch (cameraID) {
            case Constants.CAMERA_OCR:
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            //前两个参数可以为空,第三个方法是照片的回调
                            mCamera.takePicture(null, null, mPictureCallback);
                        }
                    }
                });
                break;
            case Constants.CAMERA_FACE:
                mCamera.takePicture(null, null, mPictureCallback);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mCamera == null) {
            mCamera = initCamera();
            if (mHolder != null) {
                startPreview(mCamera, mHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        releaseCamera();

    }

    /**
     * 初始化相机对象
     *
     * @return
     */
    private Camera initCamera() {

        Camera camera;
        try {
            camera = Camera.open(cameraID);
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }

        return camera;
    }

    /**
     * 开始预览相机内容
     */
    private void startPreview(Camera camera, SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            //将Camera预览角度进行调整
            degrees = getDisplayOritation(getDispalyRotation(), cameraID);

            mCamera.setDisplayOrientation(degrees);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解决预览变形问题
     *
     * @param sizes
     * @param w
     * @param h
     * @return
     */
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null)
            return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 设置相机参数
     */
    private void setParameters() {
        Camera.Parameters parameters = mCamera.getParameters();
        if (cameraID == Constants.CAMERA_OCR) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();//获取所有支持的camera尺寸
        List<Camera.Size> picSizeList = parameters.getSupportedPictureSizes();//获取所有支持的pic尺寸
        Camera.Size optionSize = getOptimalPreviewSize(sizeList, mSurfaceView.getHeight(), mSurfaceView.getWidth());//获取一个最为适配的camera.size
        Camera.Size picSize = getOptimalPreviewSize(picSizeList, mSurfaceView.getHeight(), mSurfaceView.getWidth());//获取一个最为适配的pic.size
        parameters.setPreviewSize(optionSize.width, optionSize.height);//把camera.size赋值到parameters
        parameters.setPictureSize(picSize.width, picSize.height);
        parameters.setPictureFormat(ImageFormat.JPEG);
        mCamera.setParameters(parameters);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setParameters();
        startPreview(mCamera, holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //先关闭,再开启
        mCamera.stopPreview();
        startPreview(mCamera, holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

    /**
     * 获取图片旋转角度
     *
     * @param degrees  旋转角度
     * @param cameraId 摄像头id 0为默认，后置摄像头 1为前置摄像头
     * @return 旋转角度
     */
    private int getDisplayOritation(int degrees, int cameraId) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - degrees + 360) % 360;
        }
        return result;
    }

    private int getDispalyRotation() {
        int i = getWindowManager().getDefaultDisplay().getRotation();
        switch (i) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
        return 0;
    }

    /**
     * 旋转图片
     *
     * @param data   图片byte数组
     * @param degree 旋转角度
     * @return
     */
    private Bitmap toRotating(byte[] data, int degree) {
        Bitmap img = BitmapFactory.decodeByteArray(data, 0, data.length);
        Matrix matrix = new Matrix();

        if (cameraID == Constants.CAMERA_FACE) {
            degree += 180;
        }
        matrix.postRotate(degree); /*翻转90度*/
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }

    /**
     * 图片转换为byte数组
     *
     * @param bitmap bitmap对象
     * @return
     */
    public byte[] getBytesByBitmap(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bitmap.getByteCount());
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * 图片转换为Base64字符串
     *
     * @param bitmap bitmap对象
     * @return
     */
    public String getBase64ByBitmap(Bitmap bitmap) {
        bitmap = comp(bitmap);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bitmap.getByteCount());
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
        String result = Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP);
        return result;
    }

    private Bitmap comp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        if (baos.toByteArray().length / 1024 > 512) {//判断如果图片大于500KB,进行压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 60, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }
}
