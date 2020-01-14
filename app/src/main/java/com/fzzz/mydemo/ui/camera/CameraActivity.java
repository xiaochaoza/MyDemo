package com.fzzz.mydemo.ui.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-09
 * update:
 */
@Route(path = Constants.PATH_APP_CAMERA)
public class CameraActivity extends BaseActivity {
    public static String base64Result;
    @BindView(R.id.image)
    ImageView image;

    @Override
    public int getLayoutId() {
        return R.layout.activity_camera;
    }

    @OnClick({R.id.bt_camera1, R.id.bt_camera2, R.id.bt_camera3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_camera1:
                PageUtil.toActivityForResult(this, Constants.PATH_APP_TAKEPIC_OLD, Constants.TAKE_PIC_OLD);
                break;
            case R.id.bt_camera2:
                PageUtil.toActivityForResult(this, Constants.PATH_APP_TAKEPIC_NEW, Constants.TAKE_PIC_NEW);
                break;
            case R.id.bt_camera3:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.TAKE_PIC_OLD || requestCode == Constants.TAKE_PIC_NEW) {
            if (resultCode == Activity.RESULT_OK) {
                byte[] bytes = Base64.decode(base64Result, Base64.NO_WRAP);
                Bitmap img = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                image.setImageBitmap(img);
            }
        }
    }
}
