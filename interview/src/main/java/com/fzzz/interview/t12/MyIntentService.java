package com.fzzz.interview.t12;

import android.app.IntentService;
import android.content.Intent;
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
public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (ThreadUtil.isMainThread()) {
            Log.e("MyIntentService", "主线程");
        } else {
            Log.e("MyIntentService", "非主线程");
        }
        BigInteger bigInteger = MathUtils.getThatLargeFactorial();
        Log.e("MyIntentService", "Wow this is a big number");
    }
}
