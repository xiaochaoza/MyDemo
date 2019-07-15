package com.fzzz.mydemo.ui.custom_view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import butterknife.BindView;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-21
 * update:
 */
@Route(path = Constants.PATH_APP_CUSTOMVIEW)
public class CustomViewActivity extends BaseActivity {
    @BindView(R.id.circle_view)
    CircleView circleView;

    @Override
    public int getLayoutID() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        circleView.requestLayout();
    }
}
