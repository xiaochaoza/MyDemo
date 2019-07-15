package com.fzzz.interview.ui;

import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fzzz.framework.Constants;
import com.fzzz.interview.BaseActivity;
import com.fzzz.interview.R;
import com.fzzz.interview.R2;
import com.fzzz.interview.t12.MyIntentService;
import com.fzzz.interview.t12.MyService;
import com.fzzz.interview.t15.DownloadService;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-13
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW)
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

    @OnClick(R2.id.bt_t13)
    public void onViewClicked3() {
        ARouter.getInstance().build(Constants.PATH_INTERVIEW_T13).navigation();
    }

    @OnClick(R2.id.bt_t15)
    public void onViewClicked4() {
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
    }

    @OnClick(R2.id.bt_t16)
    public void onViewClicked5() {
        ARouter.getInstance().build(Constants.PATH_INTERVIEW_T16).navigation();
    }

    @OnClick(R2.id.bt_t18)
    public void onViewClicked6() {
        ARouter.getInstance().build(Constants.PATH_INTERVIEW_T18).navigation();
    }

    @OnClick(R2.id.bt_t20)
    public void onViewClicked7() {
        ARouter.getInstance().build(Constants.PATH_INTERVIEW_T20).navigation();
    }

    @OnClick(R2.id.bt_t21)
    public void onViewClicked8() {
        ARouter.getInstance().build(Constants.PATH_INTERVIEW_T21).navigation();
    }

    @OnClick(R2.id.bt_t11)
    public void onViewClicked9() {
        ARouter.getInstance().build(Constants.PATH_INTERVIEW_T11_1).navigation();
    }

    @OnClick(R2.id.bt_t22)
    public void onViewClicked10() {
        ARouter.getInstance().build(Constants.PATH_INTERVIEW_T22).navigation();
    }
}
