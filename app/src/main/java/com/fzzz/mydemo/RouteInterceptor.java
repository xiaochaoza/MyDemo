package com.fzzz.mydemo;


import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.fzzz.framework.Constants;
import com.orhanobut.logger.Logger;

/**
 * description: 统一处理拦截器，可以用group的方式来分组处理，但是该方法已过时，可以全都拦截，不需要处理的用绿色通道来实现
 * author: ShenChao
 * time: 2019-08-18
 * update:
 */
@Interceptor(priority = 1)
public class RouteInterceptor implements IInterceptor {

    public static final String TAG = "RouteInterceptor";

    private Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (Constants.GROUP_APP_NEED_LOGIN.equals(postcard.getGroup())) {
            Logger.e("拦截器拦截，需要登录");
        }
        Logger.e("拦截器");
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
