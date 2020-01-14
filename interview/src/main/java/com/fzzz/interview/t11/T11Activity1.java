package com.fzzz.interview.t11;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.interview.R;
import com.fzzz.interview.R2;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW_T11_1)
public class T11Activity1 extends BaseActivity {
    public static final String TAG = "T11Activity1";

    @Override
    public int getLayoutId() {
        return R.layout.activity_t11;
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

    @OnClick(R2.id.bt_go)
    public void onClick(){
        PageUtil.toActivity(Constants.PATH_INTERVIEW_T11_2);
    }
}
