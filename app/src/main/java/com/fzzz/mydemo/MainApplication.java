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
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.android.agoo.huawei.HuaWeiRegister;

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

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
//                return super.isLoggable(priority, tag);
                return true;
            }
        });

        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey（需替换）；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(this, "5e8d93ca895ccafd9c000035", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "eb67fb3b787efbe78d690b4b45fbd19a");

        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Logger.e("注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Logger.e("注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });

        HuaWeiRegister.register(this);
    }

    public static Context getContext() {
        return context;
    }
}
