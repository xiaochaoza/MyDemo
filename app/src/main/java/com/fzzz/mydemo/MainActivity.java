package com.fzzz.mydemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setShowBack(false);
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn_async_load, R.id.btn_volley, R.id.btn_okhttp, R.id.btn_retrofit, R.id.btn_rxjava, R.id.btn_retrofit_rxjava,
            R.id.btn_eventbus, R.id.btn_glide, R.id.btn_greendao, R.id.btn_biometric_prompt, R.id.btn_recyclerview, R.id.btn_stick_recycler,
            R.id.btn_coordinator, R.id.btn_webview, R.id.btn_dialog, R.id.btn_drawerlayout, R.id.btn_toolbar, R.id.btn_zxing, R.id.btn_go_app,
            R.id.btn_lottie, R.id.btn_tablayout, R.id.btn_dagger2, R.id.btn_handler, R.id.btn_databinding, R.id.btn_windowinput,
            R.id.btn_animation, R.id.btn_gps, R.id.btn_take_pic, R.id.btn_device_info, R.id.btn_mytest, R.id.btn_thread, R.id.btn_single_task,
            R.id.btn_custom_view, R.id.btn_fragment, R.id.btn_leak, R.id.btn_forum, R.id.btn_swipe_refresh, R.id.btn_viewstub,  R.id.btn_go_shoushi,
            R.id.btn_wifi, R.id.btn_picker_view, R.id.btn_download, R.id.btn_login, R.id.btn_floating, R.id.btn_photo_view, R.id.btn_gson})
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
                PageUtil.toActivityGroup(Constants.PATH_APP_EVENTBUS, Constants.GROUP_APP_NEED_LOGIN);
                break;
            case R.id.btn_glide:
                PageUtil.toActivity(Constants.PATH_APP_GLIDE);
                break;
            case R.id.btn_greendao:
                break;
            case R.id.btn_biometric_prompt:
                PageUtil.toActivity(Constants.PATH_APP_BIOMETRICPROMPT);
                break;
            case R.id.btn_recyclerview:
                PageUtil.toActivity(Constants.PATH_APP_RECYCLER_LIST);
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
            case R.id.btn_download:
                PageUtil.toActivity(Constants.PATH_APP_DOWNLOAD);
                break;
            case R.id.btn_login:
                break;
            case R.id.btn_floating:
                PageUtil.toActivity(Constants.PATH_APP_FLOATING);
                break;
            case R.id.btn_photo_view:
                PageUtil.toActivity(Constants.PATH_APP_PHOTO_VIEW);
                break;
            case R.id.btn_gson:
                PageUtil.toActivity(Constants.PATH_APP_GSON);
                break;
                case R.id.btn_go_shoushi:
                PageUtil.toActivity(Constants.PATH_APP_GESTURE);
                break;
            case R.id.btn_go_app:
                Intent intent = new Intent();
                intent.setData(Uri.parse("nxzw://pay"));
                intent.putExtra("merName", "xxx");
                intent.putExtra("orderDate", "xxx");
                intent.putExtra("orderId", "xxx");
                intent.putExtra("goodsId", "xxx");
                intent.putExtra("goodsName", "xxx");
                intent.putExtra("goodsNum", "xxx");
                intent.putExtra("carriageAmt", "xxx");
                intent.putExtra("amount", "xxx");
                intent.putExtra("merId", "xxx");
                intent.putExtra("userId", "xxx");
                intent.putExtra("platId", "xxx");
                intent.putExtra("expireTime", "xxx");
                intent.putExtra("eWalletFlag", "xxx");
                intent.putExtra("sheBaoCardFlag", "xxx");
                intent.putExtra("wxpayflag", "xxx");
                intent.putExtra("alipayflag", "xxx");
                intent.putExtra("icbcpayflag", "xxx");
                intent.putExtra("eWalletStatus", "xxx");
                intent.putExtra("passfreePaymentFlag", "xxx");
                intent.putExtra("eWalletBalance", "xxx");
                intent.putExtra("eWalletDiscount", "xxx");
                intent.putExtra("icbcpayDiscount", "xxx");
                intent.putExtra("alipayDiscount", "xxx");
                intent.putExtra("wxpayDiscount", "xxx");
                intent.putExtra("rank", "xxx");
                intent.putExtra("openId","xxx");
                intent.putExtra("appReturnUrl", "xxx");
                intent.putExtra("reserved", "xxx");
                intent.putExtra("orderType", "xxx");
                intent.putExtra("remark1", "xxx");
                intent.putExtra("payPlat", "xxx");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String message) {
        if ("123".equals(message)) {
            ToastUtil.show("收到消息");
        }
    }
}
