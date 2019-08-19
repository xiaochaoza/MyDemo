package com.fzzz.mydemo.ui.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.fzzz.mydemo.R;

import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-19
 * update:
 */
public class CustomLineView extends LinearLayout {
    public CustomLineView(Context context) {
        super(context);
        init();
    }

    public CustomLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_custom_line_view, this);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        setLayoutParams(params);
        ButterKnife.bind(this, this);
    }
}
