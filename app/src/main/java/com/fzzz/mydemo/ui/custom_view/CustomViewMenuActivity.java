package com.fzzz.mydemo.ui.custom_view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.R;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-21
 * update:
 */
@Route(path = Constants.PATH_APP_CUSTOMVIEW_MENU)
public class CustomViewMenuActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_custom_menu;
    }

    @OnClick({R.id.btn_custom_circle, R.id.btn_custom_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_custom_circle:
                ARouter.getInstance()
                        .build(Constants.PATH_APP_CUSTOMVIEW)
                        .withString("type", "circle")
                        .navigation();
                break;
            case R.id.btn_custom_menu:
                ARouter.getInstance()
                        .build(Constants.PATH_APP_CUSTOMVIEW)
                        .withString("type", "menu")
                        .navigation();
                break;
        }
    }
}
