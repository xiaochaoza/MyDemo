package com.fzzz.interview.t15;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.fzzz.interview.ThreadUtil;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
public class DownloadService extends Service {
    static String pointName = "Point B";

    static final Runnable RUNNABLE = new Runnable() {
        @Override
        public void run() {
            Log.e("Service", pointName);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service", "on Create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "Point A");

        pointName = "Point C";
        new Thread(RUNNABLE).start();

        if (ThreadUtil.isMainThread()) {
            Log.e("Service", "main thread");
        } else {
            Log.e("Service", "no main thread");
        }

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
