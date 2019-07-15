package com.fzzz.interview.t22;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * description:
 * author: ShenChao
 * time: 2019-07-15
 * update:
 */
public class T22Service extends Service {
    public static final String TAG = "T22Service";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "service onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "service onDestroy");
    }

    //此方法是为了可以在Acitity中获得服务的实例
    class ServiceBinder extends Binder {
        public T22Service getService() {
            return T22Service.this;
        }
    }
}
