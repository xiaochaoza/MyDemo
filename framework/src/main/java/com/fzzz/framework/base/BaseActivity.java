package com.fzzz.framework.base;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fzzz.framework.utils.ActivityManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.message.PushAgent;

import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    private boolean showBack = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        beforeSetContentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (showBack) {
            //设置左上角返回键
            if (null == getSupportActionBar()) {
                return;
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        PushAgent.getInstance(this).onAppStart();
    }

    public void beforeSetContentView() {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //左上角返回键的id：android.R.id.home
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public abstract int getLayoutId();

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
    }
}
