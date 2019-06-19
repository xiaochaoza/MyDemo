package com.fzzz.interview.t20;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW_T20)
public class T20Activity extends AppCompatActivity {
    public static final String TAG = "T20Activity";

    //执行一次
    static {
        Log.e(TAG, "static");
    }

    //activity重新创建都会重新执行
    {
        Log.e(TAG, "local");
//        showLog();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        showLog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged");
        showLog();
    }

    private void showLog() {
        int screenNum = getResources().getConfiguration().orientation;
        switch (screenNum) {
            case 1:
                Log.e(TAG, "当前竖屏");
                break;
            case 2:
                Log.e(TAG, "当前横屏");
                break;
        }
    }
}
