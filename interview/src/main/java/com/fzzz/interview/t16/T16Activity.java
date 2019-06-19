package com.fzzz.interview.t16;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.interview.R;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW_T16)
public class T16Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        CustonView custonView = new CustonView(this);
        setContentView(R.layout.activity_t16);
    }

}
