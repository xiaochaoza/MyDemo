package com.fzzz.interview.t16;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-17
 * update:
 */
public class CustonView extends View {
    private final Paint paint = new Paint();

    public CustonView(Context context) {
        super(context);
    }

    public CustonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.save();

        canvas.translate(
                100.0f, //x
                50.0f   //y
        );

        canvas.restore();

        canvas.translate(
                100.0f, //x
                50.0f   //y
        );

        canvas.translate(
                100.0f, //x
                50.0f   //y
        );

        canvas.drawCircle(
                0.0f,       //certerX
                0.0f,       //centerY
                1.0f,    //radius
                paint
        );
    }
}
