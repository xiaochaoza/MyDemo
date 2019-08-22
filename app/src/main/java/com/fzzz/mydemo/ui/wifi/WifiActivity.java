package com.fzzz.mydemo.ui.wifi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-20
 * update:
 */
@Route(path = Constants.PATH_APP_WIFI)
public class WifiActivity extends BaseActivity {

    public static final String TAG = "WifiActivity";
    @BindView(R.id.image)
    ImageView image;

    @Override
    public int getLayoutID() {
        return R.layout.activity_wifi;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.bt_search_wifi, R.id.bt_manual_search, R.id.bt_auto_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_search_wifi:
                Glide.with(this)
                        .load("https://218.95.135.201/images/govinfo/544cd2d5-105c-46ca-b961-a429b48ef15b.jpg")
                        .into(image);
                break;
            case R.id.bt_manual_search:
                break;
            case R.id.bt_auto_search:
                break;
        }
    }


}
