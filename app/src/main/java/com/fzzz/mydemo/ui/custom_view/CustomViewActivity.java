package com.fzzz.mydemo.ui.custom_view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

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
    @BindView(R.id.ll_show)
    LinearLayout llShow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(type)) {
            return;
        }
        switch (type) {
            case "circle":
                circleView.requestLayout();
                break;
            case "menu":
                circleView.setVisibility(View.GONE);
                llShow.setWeightSum(4);
                for (int i = 0; i < 4; i++) {
                    CustomLineView customLineView = new CustomLineView(this);
                    llShow.addView(customLineView);
                }
                break;
            default:
                break;
        }
    }
}
