package com.fzzz.mydemo;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fzzz.framework.utils.DeviceUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

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
        // These two lines must be written before init, otherwise these configurations will be invalid in the init process
        if (BuildConfig.DEBUG) {
            // Print log
            ARouter.openLog();
            // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
            ARouter.openDebug();
        }
        //路由初始化
        ARouter.init(this);

        //logger初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                // （可选）是否显示线程信息。默认值true
                .showThreadInfo(false)
                // (可选）要显示的方法行数。默认值2
                .methodCount(1)
                // （可选）隐藏内部方法调用到偏移量。默认值5
                .methodOffset(0)
                // （可选）更改要打印的日志策略。默认LogCat
                .logStrategy(new LogcatLogStrategy())
                // （可选）每个日志的全局标记。默认Default PRETTY_LOGGER
                .tag(DeviceUtil.getAppName(this))
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
//                return super.isLoggable(priority, tag);
                return true;
            }
        });
    }

    public static Context getContext() {
        return context;
    }
}
