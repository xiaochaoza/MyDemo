package com.fzzz.mydemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-28
 * update:
 */
public class ToastUtil {
    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
