package com.fzzz.mydemo;


import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.fzzz.mydemo.utils.ToastUtil;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-18
 * update:
 */
@Interceptor(priority = 1)
public class RouteIntercepter implements IInterceptor {

    public static final String TAG = "RouteIntercepter";

    private Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e(TAG, "拦截器");
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
