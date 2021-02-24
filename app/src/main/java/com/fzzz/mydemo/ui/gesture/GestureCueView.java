package com.fzzz.mydemo.ui.gesture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.fzzz.mydemo.R;

import java.util.ArrayList;


/**
 * description: 小的手势密码样式
 * author: ShenChao
 * time: 2019/5/24
 * update:
 */
public class GestureCueView extends View {

    private Paint mPaint;

    private Bitmap bitmap_circle_nor, bitmap_circle_press;
    private Bitmap bitmap_line_pressed, bitmap_line_error;
    private int width, height;
    private int bitmapR;
    private ArrayList<GestureLockView.GestureLockPoint> selectedPointsList;
    private GestureLockView.GestureLockPoint[][] points;

    //        private static final int PADIN = 5; // 单位:dp
    private int paddingIn = 20; // 九宫格距离父控件左右间距

    private boolean isInit; // 初始化九宫格一次

    public GestureCueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() { // 初始化资源
        points = new GestureLockView.GestureLockPoint[3][3];
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedPointsList = new ArrayList<>();

        bitmap_circle_nor = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_circle_small_nor);
        bitmap_circle_press = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_circle_small_press);
        bitmap_line_pressed = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_line_small_press);
        bitmap_line_error = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_line_error);
        paddingIn = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, paddingIn, getResources().getDisplayMetrics());
        bitmapR = bitmap_circle_nor.getWidth();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = bitmapR + paddingIn * 2 + bitmapR * 3;
        height = width;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPoints();
        line2Canvas(canvas);
        point2Canvas(canvas);
        /**
         * 防止内存泄露
         */
        selected = null;
        selectedPointsList = null;
    }

    /**
     * 画线 根据限定条件
     *
     * @param canvas
     */
    private void line2Canvas(Canvas canvas) {
        if (selectedPointsList!=null&&selectedPointsList.size() > 0) {
            GestureLockView.GestureLockPoint pStart = selectedPointsList.get(0);
            for (GestureLockView.GestureLockPoint pEnd : selectedPointsList) {
                if (pStart != pEnd) {
                    line2Canvas(canvas, pStart, pEnd);
                    pStart = pEnd;
                }
            }
            if (selectedPointsList != null) {
                selectedPointsList.clear();
            }
        }
    }

    /**
     * 画线
     *
     * @param canvas 画布
     * @param pStart 起始点
     * @param pEnd   终结点
     */
    private void line2Canvas(Canvas canvas, GestureLockView.GestureLockPoint pStart, GestureLockView.GestureLockPoint pEnd) {
        float degree = GestureLockView.GestureLockPoint.degree(pStart, pEnd);
        canvas.rotate(degree, pStart.getPointX(), pStart.getPointY());
        Matrix matrix = new Matrix();
        matrix.setScale(1f + GestureLockView.GestureLockPoint.distance(pStart, pEnd) * 1.0f / bitmap_line_pressed.getWidth(), 2f);
        matrix.postTranslate(pStart.getPointX() * 1.0f - bitmap_line_pressed.getWidth() * 1.0f / 2, pStart.getPointY() * 1.0f - bitmap_line_pressed.getWidth() * 1.0f);
        if (pStart.getState() == GestureLockView.GestureLockPoint.STATE_PRESS) {
            canvas.drawBitmap(bitmap_line_pressed, matrix, mPaint);
        } else if (pStart.getState() == GestureLockView.GestureLockPoint.STATE_ERROR) {
            canvas.drawBitmap(bitmap_line_error, matrix, mPaint);

        }
        canvas.rotate(-degree, pStart.getPointX(), pStart.getPointY());

    }

    /**
     * 初始化九宫格point
     */
    private void initPoints() {
        if (isInit) {
            return;
        }
        width = getWidth();
        height = getHeight();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                points[i][j] = new GestureLockView.GestureLockPoint();
                points[i][j].setPointX((int) ((j + 0.5) * bitmapR + j * paddingIn));
                points[i][j].setPointY((int) ((i + 0.5) * bitmapR + i * paddingIn));
                points[i][j].setState(GestureLockView.GestureLockPoint.STATE_NOR);
                points[i][j].setIndex(i * 3 + j);
            }
        }
        isInit = true;
    }

    /**
     * 画点
     *
     * @param canvas 画布
     */
    private void point2Canvas(Canvas canvas) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Matrix matrix = new Matrix();
                float sx = 1.0f;
                matrix.setScale(sx, sx);
                matrix.postTranslate(points[i][j].getPointX() - bitmapR * sx * 0.5f, points[i][j].getPointY() - bitmapR * sx * 0.5f);
                if (points[i][j].getState() == GestureLockView.GestureLockPoint.STATE_NOR) {
                    canvas.drawBitmap(bitmap_circle_nor, matrix, mPaint);
                } else if (points[i][j].getState() == GestureLockView.GestureLockPoint.STATE_PRESS) {
                    canvas.drawBitmap(bitmap_circle_press, matrix, mPaint);
                }
            }
        }
    }

    /**
     * 根据设置的密码字符串显示已选点
     *
     * @param psw 密码字符串
     */
    public void showSelectedPoint(String psw) {
        selected = new ArrayList<>();
        selectedPointsList = new ArrayList<>();
        int counter = 0;
        int pswLength = psw.length();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                GestureLockView.GestureLockPoint point = points[i][j];
                if (psw.contains(String.valueOf(point.getIndex() + 1))) {
                    point.setState(GestureLockView.GestureLockPoint.STATE_PRESS);
                    selectedPointsList.add(points[i][j]);
                    counter++;
                    if (pswLength == counter) {
                        break;
                    }
                }
            }
        }
        /**
         * 排序，按照画图点
         */
        selectedPointsListSort(psw);
        invalidate();
    }

    private ArrayList<GestureLockView.GestureLockPoint> selected;

    private void selectedPointsListSort(String psw) {

        for (int i = 0; i < psw.length(); i++) {
            int index = Integer.parseInt(psw.charAt(i) + "");
            for (int j = 0; j < selectedPointsList.size(); j++) {
                if (index == selectedPointsList.get(j).getIndex() + 1) {
                    selected.add(selectedPointsList.get(j));
                }
            }
        }
        selectedPointsList = selected;
    }

    /**
     * 重置已选点并刷新
     */
    public void resetAndInvalidate() {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                GestureLockView.GestureLockPoint point = points[i][j];
                point.setState(GestureLockView.GestureLockPoint.STATE_NOR);
            }
        }

        invalidate();
    }

}
