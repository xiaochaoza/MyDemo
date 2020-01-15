package com.fzzz.mydemo.ui.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-08
 * update:
 */
@Route(path = Constants.PATH_APP_WEBLOAD)
public class WebLoadActivity extends BaseActivity {
    public static final String TAG = "WebLoadActivity";
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.webview_x5)
    com.tencent.smtt.sdk.WebView webViewX5;
    private long start, end;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webload;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pbLoading.setVisibility(View.VISIBLE);
                start = System.currentTimeMillis();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbLoading.setVisibility(View.INVISIBLE);
                end = System.currentTimeMillis();
                ToastUtil.show("webView:" + (end - start) + "ms");
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pbLoading.setProgress(newProgress);
            }
        });

        webViewX5.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {
            @Override
            public void onPageStarted(com.tencent.smtt.sdk.WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                pbLoading.setVisibility(View.VISIBLE);
                start = System.currentTimeMillis();
            }

            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String s) {
                super.onPageFinished(webView, s);
                pbLoading.setVisibility(View.INVISIBLE);
                end = System.currentTimeMillis();
                ToastUtil.show("X5:" + (end - start) + "ms");
            }
        });

        webViewX5.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {
            @Override
            public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                pbLoading.setProgress(newProgress);
            }
        });
    }

    @OnClick({R.id.bt_webview_load, R.id.bt_x5_load})
    public void onViewClicked(View view) {
        String url = etAddress.getText().toString().trim();
        switch (view.getId()) {
            case R.id.bt_webview_load:
                webView.setVisibility(View.VISIBLE);
                webViewX5.setVisibility(View.GONE);
                webView.loadUrl(url);
                break;
            case R.id.bt_x5_load:
                webView.setVisibility(View.GONE);
                webViewX5.setVisibility(View.VISIBLE);
                webViewX5.loadUrl(url);
                break;
            default:
                break;
        }
    }

    /**
     * 点击返回返回上一页面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && webViewX5.canGoBack()) {
            webViewX5.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 销毁webView
     */
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            webView.destroy();
            webView = null;
        }
        if (webViewX5 != null) {
            webViewX5.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webViewX5.clearHistory();
            webViewX5.destroy();
            webViewX5 = null;
        }
        super.onDestroy();
    }

    public void webViewDemo() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final WebView webView = new WebView(this);
        webView.setLayoutParams(params);
        //激活webview为活跃状态
        webView.onResume();
        //失去焦点，变成不可见状态
        //onpause动作可以通知内核暂停所有工作，比如dom解析，plugin执行，js执行
        webView.onPause();
        //app被切换到后台时,会暂停所有的layout，parsing，js，降低cpu功耗
        webView.pauseTimers();
        //恢复状态
        webView.resumeTimers();
        //销毁webview
        //如果关闭activity后，音乐或视频还在播放，就需要销毁webview
        //注意：webview在ondestroy后，webview还绑在activity上，因为创建时传入了activity的context
        //需要先从父容器移除，然后再ondestroy
//        rootLayout.removeview(webView);
        webView.destroy();

        //是否可以后退
        webView.canGoBack();
        //后退网页
        webView.goBack();
        //是否可以前进
        webView.canGoForward();
        //前进网页
        webView.goForward();
        //以当前index为起点前进或后退到历史记录中的steps
        //正数为前进，负数为后退
        int steps = 1;
        webView.goBackOrForward(steps);

        //清除缓存
        //因为内核缓存是全局的，所以清除缓存方法针对整个app
        webView.clearCache(true);
        //清除历史记录
        //只会清除webview访问记录里的除了当前访问记录
        webView.clearHistory();
        //清除自动填写的表单数据
        webView.clearFormData();

        //websettings
        WebSettings webSettings = webView.getSettings();

        //支持js
        webSettings.setJavaScriptEnabled(true);
//        //支持插件
//        webSettings.setPluginsEnabled(true);
        //设置自适应屏幕
        //图片适应webview
        webSettings.setUseWideViewPort(true);
        //webview适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        //缩放操作
        //支持缩放，默认为true
        webSettings.setSupportZoom(true);
        //上边的true的前提下，设置内置的缩放控件，若为false，则该webview不可缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //其他操作
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //可以访问文件
        webSettings.setAllowFileAccess(true);
        //支持通过js打开窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");

        //缓存级别
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        WebSettings.LOAD_CACHE_ONLY; 不使用网络，只使用缓存
//        WebSettings.LOAD_DEFAULT; 默认，由cache-control决定是否使用缓存
//        WebSettings.LOAD_CACHE_ELSE_NETWORK; 只要本地有无论是否过期，都使用缓存数据
//        WebSettings.LOAD_NO_CACHE; 不使用缓存，只从网络获取

        //开启dom storage api
        webSettings.setDomStorageEnabled(true);
        //开启database storage api
        webSettings.setDatabaseEnabled(true);
        //开启application cache
        webSettings.setAppCacheEnabled(true);

        //设置缓存目录
        String cacheDir = getFilesDir().getAbsolutePath();
        //每个application只调用一次
        webSettings.setAppCachePath(cacheDir);
        webSettings.setAppCacheMaxSize(1024 * 10);

        //方式1. 加载一个网页：
        webView.loadUrl("http://www.google.com/");
        //方式2：加载apk包中的html页面
        webView.loadUrl("file:///android_asset/test.html");
        //方式3：加载手机本地的html页面
        webView.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");

        //打开网页时不打开浏览器，而是在webview中显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //设置加载时调用，通知用户在等待网络响应
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //设置加载完毕时调用，可以关闭进度条切换程序操作
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            //加载页面资源时调用，每一个资源（比如图片）的加载都回调用一次
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            //加载错误时调用（404）
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                switch (error.getErrorCode()) {
                    case 404:
                        view.loadUrl("file:///android_assets/error_handle.html");
                        break;
                    default:
                        break;
                }
            }

            //webview默认不处理https，显示空白，需要以下设置
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//等待证书响应
                handler.cancel();//挂起链接，默认
                handler.handleMessage(null);//其他操作
            }
        });

        //辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等。
        webView.setWebChromeClient(new WebChromeClient() {
            //获得加载进度并显示
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                }
            }

            //获取标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        //销毁
        destroy(webView);
    }

    private void destroy(WebView webView) {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
        }
        ((ViewGroup) webView.getRootView().getParent()).removeView(webView);
        webView.destroy();
        webView = null;
    }
}
