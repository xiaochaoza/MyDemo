package com.fzzz.mydemo;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public class MainApplication extends Application {

    //全局context
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        //初始化路由arouter
        if (BuildConfig.DEBUG) {   // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        //路由初始化
        ARouter.init(this);
    }

    public static Context getContext() {
        return context;
    }
}
