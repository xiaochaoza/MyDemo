package com.fzzz.mydemo.ui.launch_mode;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-20
 * update:
 */
@Route(path = Constants.PATH_APP_ACTIVITY1)
public class Activity1 extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle.setText(getClass().getSimpleName() + "=" + Count.count);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lunch_mode;
    }

    @OnClick(R.id.bt_activity1)
    public void click1() {
        Count.count++;
        PageUtil.toActivity(Constants.PATH_APP_ACTIVITY1);
    }

    @OnClick(R.id.bt_activity2)
    public void click2() {
        Count.count++;
        PageUtil.toActivity(Constants.PATH_APP_ACTIVITY2);
    }

    @OnClick(R.id.bt_activity3)
    public void click3() {
        Count.count++;
        PageUtil.toActivity(Constants.PATH_APP_ACTIVITY3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Count.count--;
    }
}
