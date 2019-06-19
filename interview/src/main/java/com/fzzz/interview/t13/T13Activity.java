package com.fzzz.interview.t13;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW_T13)
public class T13Activity extends T13BaseActivity {
    private static String staticValue = "Dog";
    private String value = "Apple";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Lifecycle", "Value is: "+ value);
        Log.e("Lifecycle", "Static value is: " + staticValue);

        if (null == savedInstanceState) {
            Log.e("Lifecycle", "Saved Value is null");
        }else {
            Log.e("Lifecycle", "Saved Value is: "+ savedInstanceState.getString("Saved value"));
        }

        value = "Orange";
        staticValue = "Cat";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Saved value", "Disco");
    }
}
