package com.fzzz.mydemo.base;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public abstract class BaseActivity extends AppCompatActivity {
    private boolean showBack = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        if (showBack) {
            //设置左上角返回键
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

    public abstract int getLayoutID();

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
    }
}
