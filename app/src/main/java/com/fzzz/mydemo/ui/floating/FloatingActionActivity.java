package com.fzzz.mydemo.ui.floating;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-30
 * update:
 */
@Route(path = Constants.PATH_APP_FLOATING)
public class FloatingActionActivity extends BaseActivity {

    @BindView(R.id.fab_button)
    FloatingActionButton fabButton;
    @BindView(R.id.ab_button)
    CustomFloatingActionButton abButton;

    @Override
    public int getLayoutID() {
        return R.layout.activity_floating;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("系统按钮被点击");
            }
        });

        abButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("自定义按钮被点击");
            }
        });
    }

}
