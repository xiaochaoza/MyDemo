package com.fzzz.interview.t13;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
public class T13BaseActivity extends AppCompatActivity {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Saved value", "Hetal");
    }
}
