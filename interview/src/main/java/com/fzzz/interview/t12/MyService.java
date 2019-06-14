package com.fzzz.interview.t12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.fzzz.interview.ThreadUtil;

import java.math.BigInteger;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-13
 * update:
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ThreadUtil.isMainThread2()) {
            Log.e("MyIntentService", "主线程");
        } else {
            Log.e("MyIntentService", "非主线程");
        }
        BigInteger bigInteger = MathUtils.getThatLargeFactorial();
        Log.e("MyService", "Wow this is a big number");
        return START_STICKY;
    }
}

