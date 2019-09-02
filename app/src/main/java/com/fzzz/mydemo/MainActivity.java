package com.fzzz.mydemo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setShowBack(false);
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn_async_load, R.id.btn_volley, R.id.btn_okhttp, R.id.btn_retrofit, R.id.btn_rxjava, R.id.btn_retrofit_rxjava,
            R.id.btn_eventbus, R.id.btn_glide, R.id.btn_greendao, R.id.btn_figerprint, R.id.btn_recyclerview, R.id.btn_stick_recycler,
            R.id.btn_coordinator, R.id.btn_webview, R.id.btn_dialog, R.id.btn_drawerlayout, R.id.btn_toolbar, R.id.btn_zxing,
            R.id.btn_lottie, R.id.btn_tablayout, R.id.btn_dagger2, R.id.btn_handler, R.id.btn_databinding, R.id.btn_windowinput,
            R.id.btn_animation, R.id.btn_gps, R.id.btn_take_pic, R.id.btn_device_info, R.id.btn_mytest, R.id.btn_thread, R.id.btn_single_task,
            R.id.btn_custom_view, R.id.btn_fragment, R.id.btn_leak, R.id.btn_forum, R.id.btn_swipe_refresh, R.id.btn_viewstub,
            R.id.btn_wifi, R.id.btn_picker_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_async_load:
                PageUtil.toActivity(Constants.PATH_APP_AYSNC_TASK);
                break;
            case R.id.btn_volley:
                break;
            case R.id.btn_okhttp:
                PageUtil.toActivity(Constants.PATH_APP_OKHTTP);
                break;
            case R.id.btn_retrofit:
                PageUtil.toActivity(Constants.PATH_APP_RETROFIT);
                break;
            case R.id.btn_rxjava:
                PageUtil.toActivity(Constants.PATH_APP_RXJAVA);
                break;
            case R.id.btn_retrofit_rxjava:
                PageUtil.toActivity(Constants.PATH_APP_RETROFIT_RXJAVA);
                break;
            case R.id.btn_eventbus:
                PageUtil.toActivity(Constants.PATH_APP_EVENTBUS);
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
                PageUtil.toActivity(Constants.PATH_APP_WEBVIEW);
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
                PageUtil.toActivity(Constants.PATH_APP_GPS);
                break;
            case R.id.btn_take_pic:
                PageUtil.toActivity(Constants.PATH_APP_CAMERA);
                break;
            case R.id.btn_device_info:
                break;
            case R.id.btn_mytest:
                PageUtil.toActivity(Constants.PATH_INTERVIEW);
                break;
            case R.id.btn_thread:
                break;
            case R.id.btn_single_task:
                PageUtil.toActivity(Constants.PATH_APP_LAUNCHMODE);
                break;
            case R.id.btn_custom_view:
                PageUtil.toActivity(Constants.PATH_APP_CUSTOMVIEW_MENU);
                break;
            case R.id.btn_fragment:
                PageUtil.toActivity(Constants.PATH_FRAGMENT_HOME);
                break;
            case R.id.btn_leak:
                PageUtil.toActivity(Constants.PATH_APP_LEAK);
                break;
            case R.id.btn_forum:
                PageUtil.toActivity(Constants.PATH_APP_FORUM);
                break;
            case R.id.btn_swipe_refresh:
                PageUtil.toActivity(Constants.PATH_APP_SWIPE_REFRESH);
                break;
            case R.id.btn_viewstub:
                PageUtil.toActivity(Constants.PATH_APP_VIEWSTUB);
                break;
            case R.id.btn_wifi:
                PageUtil.toActivity(Constants.PATH_APP_WIFI);
                break;
            case R.id.btn_picker_view:
                PageUtil.toActivity(Constants.PATH_APP_PICKER);
                break;
        }
    }
}
