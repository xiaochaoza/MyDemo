package com.fzzz.interview.ui;

import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.interview.BaseActivity;
import com.fzzz.interview.InterConstants;
import com.fzzz.interview.R;
import com.fzzz.interview.R2;
import com.fzzz.interview.t12.MyIntentService;
import com.fzzz.interview.t12.MyService;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-13
 * update:
 */
@Route(path = InterConstants.PATH_INTERVIEW)
public class InterViewActivity extends BaseActivity {
    @Override
    public int getLayoutID() {
        return R.layout.activity_inter;
    }

    @OnClick(R2.id.bt_t12_1)
    public void onViewClicked1() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    @OnClick(R2.id.bt_t12_2)
    public void onViewClicked2() {
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }
}
