package com.fzzz.mydemo;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fzzz.mydemo.base.BaseActivity;
import com.fzzz.mydemo.utils.PageUtil;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public class MainActivity extends BaseActivity {

    @OnClick({R.id.btn_async_load, R.id.btn_volley, R.id.btn_okhttp, R.id.btn_retrofit, R.id.btn_rxjava, R.id.btn_retrofit_rxjava,
            R.id.btn_eventbus, R.id.btn_glide, R.id.btn_greendao, R.id.btn_figerprint, R.id.btn_recyclerview, R.id.btn_stick_recycler,
            R.id.btn_coordinator, R.id.btn_webview, R.id.btn_dialog, R.id.btn_drawerlayout, R.id.btn_toolbar, R.id.btn_zxing,
            R.id.btn_lottie, R.id.btn_tablayout, R.id.btn_dagger2, R.id.btn_handler, R.id.btn_databinding, R.id.btn_windowinput,
            R.id.btn_animation, R.id.btn_gps, R.id.btn_take_pic, R.id.btn_device_info, R.id.btn_mytest, R.id.btn_okhttp_rxjava})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_async_load:
                break;
            case R.id.btn_volley:
                break;
            case R.id.btn_okhttp:
                PageUtil.toActivity(Constants.PATH_OKHTTP);
                break;
                case R.id.btn_okhttp_rxjava:
                PageUtil.toActivity(Constants.PATH_OKHTTP);
                break;
            case R.id.btn_retrofit:
                PageUtil.toActivity(Constants.PATH_RETROFIT);
                break;
            case R.id.btn_rxjava:
                PageUtil.toActivity(Constants.PATH_RXJAVA);
                break;
            case R.id.btn_retrofit_rxjava:
                break;
            case R.id.btn_eventbus:
                break;
            case R.id.btn_glide:
                break;
            case R.id.btn_greendao:
                break;
            case R.id.btn_figerprint:
                break;
            case R.id.btn_recyclerview:
                break;
            case R.id.btn_stick_recycler:
                break;
            case R.id.btn_coordinator:
                break;
            case R.id.btn_webview:
                break;
            case R.id.btn_dialog:
                break;
            case R.id.btn_drawerlayout:
                break;
            case R.id.btn_toolbar:
                break;
            case R.id.btn_zxing:
                break;
            case R.id.btn_lottie:
                break;
            case R.id.btn_tablayout:
                break;
            case R.id.btn_dagger2:
                break;
            case R.id.btn_handler:
                break;
            case R.id.btn_databinding:
                break;
            case R.id.btn_windowinput:
                break;
            case R.id.btn_animation:
                break;
            case R.id.btn_gps:
                break;
            case R.id.btn_take_pic:
                break;
            case R.id.btn_device_info:
                break;
            case R.id.btn_mytest:
                break;
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

}
