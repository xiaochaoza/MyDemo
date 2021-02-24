package com.fzzz.mydemo.ui.gesture;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.utils.ToastUtil;

import butterknife.BindView;

/**
 * description: 认证手势密码
 * author: ShenChao
 * time: 2019/5/24
 * update:
 */
@Route(path = Constants.PATH_APP_GESTURE_VERIFY)
public class GestureLockVerifyActivity extends BaseActivity {
    @BindView(R.id.tv_tip_geslockset)
    TextView tv_tip;
    @BindView(R.id.gv_lock)
    GestureLockView lockView;

    private String gesture = "14789";//用户手势

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lockView.setOnPatterChangeListener(onPatterChangeListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gesture_verify;
    }

    private GestureLockView.OnPatterChangeListener onPatterChangeListener = new GestureLockView.OnPatterChangeListener() {
        @Override
        public void onPatterChanged(String password) {
            if (password.length() < GestureLockConfig.MINLENGTH_PSW) { // 密码少于指定位数
                tv_tip.setText(GestureLockConfig.ERROR_LENGTH);
                tv_tip.setTextColor(ContextCompat.getColor(GestureLockVerifyActivity.this, android.R.color.holo_red_dark));
                lockView.showErrorState();
                startShake();
            } else {
                tv_tip.setText("");
                if (gesture.equals(password)) {
                    ToastUtil.show("验证成功");
                    finish();
                } else {
                    refreshView();
                }
            }
        }

        @Override
        public void onPatterStart() {
            //手势开始
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
    }
}
