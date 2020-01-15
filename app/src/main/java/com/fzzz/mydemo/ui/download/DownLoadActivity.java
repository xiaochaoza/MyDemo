package com.fzzz.mydemo.ui.download;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.FileProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.helper.RetrofitLocalHelper;
import com.fzzz.mydemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * description: Retrofit + RxJava实现文件下载
 * author: ShenChao
 * time: 2019-09-04
 * update:
 */
@Route(path = Constants.PATH_APP_DOWNLOAD)
public class DownLoadActivity extends BaseActivity {

    private Disposable disposable;
    private String path = Environment.getExternalStorageDirectory().getPath() + "/app.apk";

    @Override
    public int getLayoutId() {
        return R.layout.activity_download;
    }

    @OnClick(R.id.bt_download)
    public void onViewClicked() {
        disposable = RetrofitLocalHelper.get().downloadFile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((responseBody) -> {
                    File file = saveFile(path, responseBody);
                    return file;
                }).subscribe((file) -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    //判断是否是AndroidN以及更高的版本
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(DownLoadActivity.this, "com.fzzz.fileProvider", file);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                    } else {
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    }
                    startActivity(intent);
                });
    }

    private File saveFile(String filePath, ResponseBody body) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = null;

        try {
            if (filePath == null) {
                return null;
            }
            file = new File(filePath);
            if (file == null || !file.exists()) {
                file.createNewFile();
            }

            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;
            byte[] fileReader = new byte[4096];

            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);

            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != disposable && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
