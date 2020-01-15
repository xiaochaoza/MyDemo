package com.fzzz.mydemo.ui.asynctask;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.utils.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-29
 * update:
 */
@Route(path = Constants.PATH_APP_AYSNC_TASK)
public class AsyncTaskActivity extends BaseActivity {

    @BindView(R.id.pb_load_round)
    ProgressBar pbLoadRound;
    @BindView(R.id.pb_load_line)
    ProgressBar pbLoadLine;
    @BindView(R.id.image)
    ImageView image;
    private MyAsyncTask asyncTask;

    /**
     * 0：进度条加载，1：dialog加载
     */
    private String loadType;
    private ProgressDialog progressDialog;
    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550468908242&di=48b779b79168a94b30b3036ff1b084aa&imgtype=0&src=http%3A%2F%2Fwww.pptbz.com%2Fpptpic%2FUploadFiles_6909%2F201203%2F2012031220134655.jpg";

    @Override
    public int getLayoutId() {
        return R.layout.activity_async_task;
    }

    @OnClick({R.id.bt_load_img1, R.id.bt_load_img2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_load_img1:
                loadType = "0";
                new MyAsyncTask().execute(url);
                break;
            case R.id.bt_load_img2:
                loadType = "1";
                initDialog();
                asyncTask = new MyAsyncTask();
                asyncTask.execute(url);
                break;
            default:
                break;
        }
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(this);
        //设置标题
        progressDialog.setTitle("提示");
        //设置显示的信息
        progressDialog.setMessage("亲、正在玩命儿加载...请稍后...");
        //设置弹出框为水平进度加载模式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    asyncTask.cancel(true);
                    progressDialog.dismiss();
                    ToastUtil.show("下载已取消");
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    /**
     * AsyncTask<Params,Progress,Reuslt>是一个抽象类，通常用于被继承，继承AsyncTask需要指定如下三个参数
     * 1、Params ：启动任务时输入参数的类型。
     * 2、Progress：后台任务执行中返回进度值的类型。
     * 3、Result：后台执行任务完成后返回结果的类型。
     */
    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        /**
         * 执行后台耗时操作时被调用，通常用户完成一些初始化操作。
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            switch (loadType) {
                case "0":
                    pbLoadRound.setVisibility(View.VISIBLE);
                    pbLoadLine.setVisibility(View.VISIBLE);
                    break;
                case "1":
                    progressDialog.show();
                    break;
                default:
                    break;
            }
            image.setImageBitmap(null);
        }

        /**
         * 在doInBackground()方法中调用publishProgress()方法，更新任务的执行进度后，就会触发该方法。（获取进度跟新进度条）
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //计算进度
            int current = values[0];
            int length = values[1];

            switch (loadType) {
                case "0":
                    //length不转float会导致没有小数，结果一直为0
                    int progress = (int) (current / (float) length * 100);
                    pbLoadLine.setProgress(progress);
                    break;
                case "1":
                    progressDialog.setMax(length);
                    progressDialog.setProgress(current);
                    break;
                default:
                    break;
            }
        }

        /**
         * 必须重写，异步执行后台线程将要完成的任务。
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            String imgUrl = strings[0];
            int current = 0;

            if (isCancelled()) {
                return null;
            }
            try {
                URL url = new URL(imgUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(300);
                int length = connection.getContentLength();
                int code = connection.getResponseCode();
                if (200 == code) {
                    InputStream is = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int len;
                    byte[] bytes = new byte[1024];

                    while ((len = is.read(bytes)) != -1) {
                        TimeUnit.MILLISECONDS.sleep(200);
                        //追加进度
                        current += len;
                        bos.write(bytes, 0, len);
                        publishProgress(current, length);
                    }

                    byte[] bitmapBytes = bos.toByteArray();
                    return BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 当doInBackground()完成后，系统会自动调用onPostExecute()方法，并将doInBackground方法返回的值传给该方法。
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            switch (loadType) {
                case "0":
                    pbLoadRound.setVisibility(View.INVISIBLE);
                    pbLoadLine.setVisibility(View.INVISIBLE);
                    break;
                case "1":
                    progressDialog.dismiss();
                    break;
                default:
                    break;
            }
            image.setImageBitmap(bitmap);
        }
    }
}
