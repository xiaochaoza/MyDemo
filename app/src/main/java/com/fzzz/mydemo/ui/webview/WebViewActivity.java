package com.fzzz.mydemo.ui.webview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-08
 * update:
 */
@Route(path = Constants.PATH_APP_WEBVIEW)
public class WebViewActivity extends BaseActivity {
    public static final String TAG = "WebViewActivity";

    @BindView(R.id.iv_show)
    ImageView ivShow;

    private List<Intent> cameraIntents = new ArrayList<>();
    private Uri imageUri;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @OnClick({R.id.bt_js_bridge, R.id.bt_js_camera, R.id.bt_system_camera, R.id.iv_show, R.id.bt_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_js_bridge:
                PageUtil.toActivity(Constants.PATH_APP_JS_BRIDGE);
                break;
            case R.id.bt_js_camera:
                PageUtil.toActivity(Constants.PATH_APP_JS_CAMERA);
                break;
            case R.id.bt_system_camera:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, Constants.PERMISSIONS_WRITE_EXTERNAL_STORAGE, 0);
                    return;
                }
                File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "1");
                if (!imageStorageDir.exists()) {
                    imageStorageDir.mkdirs();
                }
                File file = new File(imageStorageDir + File.separator + "IMG_" + System.currentTimeMillis() + ".jpg");
                imageUri = Uri.fromFile(file);
                //创建intent ，设置action调取相机
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntents.add(i);
                //设置去选择文件的界面
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //选择文件并带回
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //图片类型文件
                intent.setType("image/*");
                Intent chooserIntent = Intent.createChooser(intent, "请选择图片获取方式");
                //将多个intent的集合转成数组，一起传给新intent
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
                startActivityForResult(chooserIntent, 500);
                cameraIntents.clear();
                break;
            case R.id.bt_load:
//                PageUtil.toActivity(Constants.PATH_APP_WEBLOAD);
                ARouter.getInstance().build(Constants.PATH_APP_WEBLOAD).withString("url", "http://www.baidu.com").navigation();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (500 == requestCode) {
            ivShow.setImageURI(imageUri);
        }
    }
}
