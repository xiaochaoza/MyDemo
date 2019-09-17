package com.fzzz.interview.t18;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.interview.R;

import org.greenrobot.eventbus.EventBus;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW_T18)
public class T18Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CustonView custonView = new CustonView(this);
        setContentView(R.layout.activity_t16);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post("123");

    }
}
