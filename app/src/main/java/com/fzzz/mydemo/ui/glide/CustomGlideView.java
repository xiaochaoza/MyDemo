package com.fzzz.mydemo.ui.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * description:
 * author: ShenChao
 * time: 2020-01-14
 * update:
 */
public class CustomGlideView extends FrameLayout {
    private ImageView imageView;

    public CustomGlideView(@NonNull Context context) {
        super(context);
    }

    public CustomGlideView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGlideView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageView = new ImageView(getContext());
        addView(imageView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public void setImage(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }
}
