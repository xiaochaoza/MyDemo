package com.fzzz.mydemo.ui.webview;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description: 简单桥接
 * author: ShenChao
 * time: 2019-05-31
 * update:
 */
@Route(path = Constants.PATH_APP_JS_BRIDGE)
public class JsWebActivity extends BaseActivity {
    @BindView(R.id.wv_js_bridge)
    WebView webview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_js_bridge;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置编码
        webview.getSettings().setDefaultTextEncodingName("utf-8");
        //设置支持js
        webview.getSettings().setJavaScriptEnabled(true);
        //设置允许弹窗
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webview.addJavascriptInterface(new Contact() {
            @JavascriptInterface
            @Override
            public void callAndroid(String text) {
                ToastUtil.show(text);
            }
        }, "native");
        //加载路径
        webview.loadUrl("file:///android_asset/web.html");
    }

    @OnClick(R.id.bt_call_h5)
    public void onViewClicked() {
        webview.loadUrl("javascript:callH5('Android调h5成功')");
    }

    interface Contact {
        @JavascriptInterface
        void callAndroid(String text);
    }
}
