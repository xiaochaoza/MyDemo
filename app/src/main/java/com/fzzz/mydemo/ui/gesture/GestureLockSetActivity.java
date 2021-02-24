package com.fzzz.mydemo.ui.gesture;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * description: 设置手势密码
 * author: ShenChao
 * time: 2019/5/24
 * update:
 */
@Route(path = Constants.PATH_APP_GESTURE_SET)
public class GestureLockSetActivity extends BaseActivity {
    @BindView(R.id.tv_tip_geslockset)
    TextView tv_tip;
    @BindView(R.id.gv_cue)
    GestureCueView lockTipView;
    @BindView(R.id.gv_lock)
    GestureLockView lockView;

    private String firstEnterPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lockView.setOnPatterChangeListener(onPatterChangeListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gesture_set;
    }

    private GestureLockView.OnPatterChangeListener onPatterChangeListener = new GestureLockView.OnPatterChangeListener() {
        @Override
        public void onPatterChanged(String password) {
            if (null == firstEnterPsw) { // 首次密码设置
                if (password.length() < GestureLockConfig.MINLENGTH_PSW) { // 密码少于指定位数
                    firstEnterPsw = null;
                    tv_tip.setText(GestureLockConfig.ERROR_LENGTH);
                    tv_tip.setTextColor(ContextCompat.getColor(GestureLockSetActivity.this, android.R.color.holo_red_dark));
                    lockView.showErrorState();
                    startShake();
                } else {
                    lockTipView.resetAndInvalidate();
                    lockTipView.showSelectedPoint(password);
                    lockView.resetAndInvalidate();
                    tv_tip.setTextColor(ContextCompat.getColor(GestureLockSetActivity.this, R.color.color999));
                    tv_tip.setText(GestureLockConfig.INPUT_AGAIN);
                    firstEnterPsw = password;
                }
            } else {
                // 密码设置成功
                if (password.equals(firstEnterPsw)) {
                    ToastUtil.show("设置成功:" + password);
                    finish();
                } else {
                    lockView.showErrorState();
                    tv_tip.setTextColor(ContextCompat.getColor(GestureLockSetActivity.this, android.R.color.holo_red_dark));
                    lockTipView.resetAndInvalidate();
                    tv_tip.setText(GestureLockConfig.ERROR_INPUT);
                    startShake();
                    firstEnterPsw = null;
                }
            }
        }

        @Override
        public void onPatterStart() {
            //手势设置开始
        }
    };

    private void startShake() {
        TranslateAnimation animation = new TranslateAnimation(0, -5, 0, 0);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(50);
        animation.setRepeatCount(4);
        animation.setRepeatMode(Animation.REVERSE);
        tv_tip.startAnimation(animation);
    }

    /**
     * 初始化view
     */
    private void refreshView() {
        lockView.resetAndInvalidate();
        lockTipView.resetAndInvalidate();
    }
}
