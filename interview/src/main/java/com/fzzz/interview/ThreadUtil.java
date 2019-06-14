package com.fzzz.interview;

import android.os.Looper;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-13
 * update:
 */
public class ThreadUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isMainThread2() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

}
