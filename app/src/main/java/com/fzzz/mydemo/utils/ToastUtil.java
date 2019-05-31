package com.fzzz.mydemo.utils;

import android.widget.Toast;

import com.fzzz.mydemo.MainApplication;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-28
 * update:
 */
public class ToastUtil {
    public static void show(String message) {
        Toast.makeText(MainApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
