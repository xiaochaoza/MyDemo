package com.fzzz.mydemo.ui.gesture;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.R;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2021/2/24
 * update:
 */
@Route(path = Constants.PATH_APP_GESTURE)
public class GestureActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_gesture;
    }

    @OnClick({R.id.btn_gesture_set, R.id.btn_gesture_verify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_gesture_set:
                PageUtil.toActivity(Constants.PATH_APP_GESTURE_SET);
                break;
            case R.id.btn_gesture_verify:
                PageUtil.toActivity(Constants.PATH_APP_GESTURE_VERIFY);
                break;
        }
    }
}
