package com.fzzz.mydemo.ui.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.fzzz.mydemo.R;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-21
 * update:
 */
public class CircleView extends View {
    private int circleColor, arcColor, textColor, startAngle, sweepAngle, widthSpecMode, widthSpecSize, heightSpecMode, heightSpecSize, mCircleXY;
    private String text;
    private float textSize, mRadius;
    private Paint mCirclePaint, mArcPaint, mTextPaint;
    private RectF mRectF;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        if (null != ta) {
            circleColor = ta.getColor(R.styleable.CircleView_circleColor, 0);
            arcColor = ta.getColor(R.styleable.CircleView_arcColor, 0);
            textColor = ta.getColor(R.styleable.CircleView_textColor, 0);
            textSize = ta.getDimension(R.styleable.CircleView_textSize, 20);
            text = ta.getString(R.styleable.CircleView_text);
            startAngle = ta.getInt(R.styleable.CircleView_startAngle, 0);
            sweepAngle = ta.getInt(R.styleable.CircleView_sweepAngle, 20);
            ta.recycle();
        }
    }

    /*
        Paint.ANTI_ALIAS_FLAG ：抗锯齿标志
        Paint.FILTER_BITMAP_FLAG : 使位图过滤的位掩码标志
        Paint.DITHER_FLAG : 使位图进行有利的抖动的位掩码标志
        Paint.UNDERLINE_TEXT_FLAG : 下划线
        Paint.STRIKE_THRU_TEXT_FLAG : 中划线
        Paint.FAKE_BOLD_TEXT_FLAG : 加粗
        Paint.LINEAR_TEXT_FLAG : 使文本平滑线性扩展的油漆标志
        Paint.SUBPIXEL_TEXT_FLAG : 使文本的亚像素定位的绘图标志
        Paint.EMBEDDED_BITMAP_TEXT_FLAG : 绘制文本时允许使用位图字体的绘图标志
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int length = Math.min(widthSpecSize, heightSpecSize);
        mCircleXY = length / 2;
        mRadius = length * 0.1f / 2;
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(circleColor);
        mRectF = new RectF(length * 0.1f, length * 0.1f, length * 0.9f,
                length * 0.9f);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setColor(arcColor);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth((widthSpecSize * 0.1f));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, mArcPaint);
        canvas.drawText(text, 0, text.length(), mCircleXY, mCircleXY + textSize / 4, mTextPaint);
    }
}
