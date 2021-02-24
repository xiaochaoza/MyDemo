package com.fzzz.mydemo.ui.gesture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.fzzz.mydemo.R;

import java.util.ArrayList;

/**
 * description: 九宫格密码样式
 * author: ShenChao
 * time: 2019/5/24
 * update:
 */
public class GestureLockView extends View {

    private Paint mPaint;
    private Bitmap bitmap_circle_nor, bitmap_circle_press, bitmap_circle_error, bitmap_line_pressed, bitmap_line_error;
    //    private int offsetX, offsetY; // view的x,y轴偏移量
    private int movingX, movingY; // 当前手指所在位置x,y坐标
    private GestureLockPoint curPoint; // 当前手指触碰的圈(不在九宫格内则为null)
    private boolean isStart; // 绘制开始
    private boolean isFinish; // 绘制结束
    private ArrayList<GestureLockPoint> selectedPointsList; // 已选中点列表
    private GestureLockPoint[][] points;

    private boolean isInit; // 初始化九宫格一次

    //    private int width; // 九宫格view的宽高
    private int paddingIn = 56; // 九宫格相邻两圈的间距 单位:dp
    private int bitmapR = 46; // 九宫格圈的目标大小(直径) 单位:dp


    public GestureLockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() { // 初始化资源
        points = new GestureLockPoint[3][3];
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedPointsList = new ArrayList<>();
        bitmap_circle_nor = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_circle_great_nor);
        bitmap_circle_press = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_circle_great_press);
        bitmap_circle_error = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_circle_great_error);
        bitmap_line_pressed = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_line_press);
        bitmap_line_error = BitmapFactory.decodeResource(getResources(), R.mipmap.gesture_line_error);
        paddingIn = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, paddingIn, getResources().getDisplayMetrics());
        bitmapR = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, bitmapR, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        width = getMeasuredWidth();
        // 九宫格绘制大小为手机宽度(竖屏状态)
        setMeasuredDimension(2 * paddingIn + bitmapR * 3, 2 * paddingIn + bitmapR * 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPoints();
        line2Canvas(canvas);
        point2Canvas(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isFinish = false;
        movingX = (int) event.getX();
        movingY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (null != onPatterChangeListener) {
                    onPatterChangeListener.onPatterStart();
                }
                reset();
                curPoint = isXYInPoint(movingX, movingY);
                isStart = null != curPoint;
                if (isStart) {
                    mHandler.removeCallbacks(runnable);
                    curPoint.state = GestureLockPoint.STATE_PRESS;
                    selectedPointsList.add(curPoint);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                curPoint = isXYInPoint(movingX, movingY);
                if (isStart && null != curPoint && !selectedPointsList.contains(curPoint)) {
                    if (selectedPointsList.size() != 0) {
                        GestureLockPoint prePoint = selectedPointsList.get(selectedPointsList.size() - 1);
                        int x = prePoint.pointX + curPoint.pointX;
                        int y = prePoint.pointY + curPoint.pointY;
                        GestureLockPoint newPoint = isXYInPoint(x / 2, y / 2);
                        if (newPoint != null && !selectedPointsList.contains(newPoint)) {
                            newPoint.state = GestureLockPoint.STATE_PRESS;
                            selectedPointsList.add(newPoint);
                        }
                        if (newPoint != curPoint) {
                            curPoint.state = GestureLockPoint.STATE_PRESS;
                            selectedPointsList.add(curPoint);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isFinish = true;
                break;
        }
        if (isFinish) {
            if (selectedPointsList.size() <= 1) {
                reset();
            } else if (null != onPatterChangeListener) {
                StringBuilder sb = new StringBuilder();
                for (GestureLockPoint point : selectedPointsList) {
                    sb.append(point.index + 1);
                }
                onPatterChangeListener.onPatterChanged(sb.toString());
            }
        }
        invalidate();
        return true;
    }

    /**
     * 重置已选点
     */
    private void reset() {
        for (GestureLockPoint point : selectedPointsList) {
            point.state = GestureLockPoint.STATE_NOR;
        }
        selectedPointsList.clear();
    }

    /**
     * 重置已选点并刷新
     */
    public void resetAndInvalidate() {
        reset();
        invalidate();
    }

    /**
     * 判断x，y坐标所在的位置是否为九宫格中的点,是则返回该点
     *
     * @param x
     * @param y
     * @return
     */
    @Nullable
    private GestureLockPoint isXYInPoint(int x, int y) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (isXYInPoint(x, y, points[i][j])) {
                    return points[i][j];
                }
            }
        }
        return null;
    }

    /**
     * 判断x，y坐标所在的位置是否在给定point点内
     *
     * @param x
     * @param y
     * @param point
     * @return
     */
    private boolean isXYInPoint(int x, int y, GestureLockPoint point) {
        if (point == null) {
            return false;
        }
        return Math.sqrt((double) ((point.pointX - x) * (point.pointX - x) + (point.pointY - y) * (point.pointY - y)))
                < bitmapR * 0.6;
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
                matrix.postTranslate(points[i][j].pointX - bitmapR * sx * 0.5f, points[i][j].pointY - bitmapR * sx * 0.5f);
                if (points[i][j].state == GestureLockPoint.STATE_NOR) {
                    canvas.drawBitmap(bitmap_circle_nor, matrix, mPaint);
                } else if (points[i][j].state == GestureLockPoint.STATE_PRESS) {
                    canvas.drawBitmap(bitmap_circle_press, matrix, mPaint);
                } else if (points[i][j].state == GestureLockPoint.STATE_ERROR) {
                    canvas.drawBitmap(bitmap_circle_error, matrix, mPaint);
                }
            }
        }
    }

    /**
     * 画线 根据限定条件
     *
     * @param canvas
     */
    private void line2Canvas(Canvas canvas) {
        if (selectedPointsList.size() > 0) {
            GestureLockPoint pStart = selectedPointsList.get(0);
            for (GestureLockPoint pEnd : selectedPointsList) {
                if (pStart != pEnd) {
                    line2Canvas(canvas, pStart, pEnd);
                    pStart = pEnd;
                }
            }
            if (!isFinish) {
                line2Canvas(canvas, pStart, new GestureLockPoint(movingX, movingY));
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
    private void line2Canvas(Canvas canvas, GestureLockPoint pStart, GestureLockPoint pEnd) {
        float degree = GestureLockPoint.degree(pStart, pEnd);
        canvas.rotate(degree, pStart.pointX, pStart.pointY);
        Matrix matrix = new Matrix();
        matrix.setScale(1f + GestureLockPoint.distance(pStart, pEnd) * 1.0f / bitmap_line_pressed.getWidth(), 2f);
        matrix.postTranslate(pStart.pointX * 1.0f - bitmap_line_pressed.getWidth() * 1.0f / 2, pStart.pointY * 1.0f - bitmap_line_pressed.getWidth() * 1.0f);
        if (pStart.state == GestureLockPoint.STATE_PRESS) {
            canvas.drawBitmap(bitmap_line_pressed, matrix, mPaint);
        } else if (pStart.state == GestureLockPoint.STATE_ERROR) {
            canvas.drawBitmap(bitmap_line_error, matrix, mPaint);
        }
        canvas.rotate(-degree, pStart.pointX, pStart.pointY);
    }

    /**
     * 初始化九宫格point
     */
    private void initPoints() {
        if (isInit) {
            return;
        }
        //设置九宫格x，y偏移量
//        offsetX = (width - paddingIn * 2 - bitmapR * 3) / 2;
//        offsetY = offsetX;
        resize();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                points[i][j] = new GestureLockPoint();
                points[i][j].pointX = (int) ((j + 0.5) * bitmapR + j * paddingIn);
                points[i][j].pointY = (int) ((i + 0.5) * bitmapR + i * paddingIn);
                points[i][j].state = GestureLockPoint.STATE_NOR;
                points[i][j].index = i * 3 + j;
            }
        }
        isInit = true;
    }

    // 丈量九宫格中每个圈的大小 －－ 根据左右间距和圈之间间距来决定
    private void resize() {
        Matrix matrix = new Matrix();
        int w = bitmap_circle_nor.getWidth();
        float scaleX = (float) (1.0 * bitmapR / w);
        matrix.postScale(scaleX, scaleX);
        bitmap_circle_nor = Bitmap.createBitmap(bitmap_circle_nor, 0, 0, w, w, matrix, true);
        bitmap_circle_press = Bitmap.createBitmap(bitmap_circle_press, 0, 0, w, w, matrix, true);
        bitmap_circle_error = Bitmap.createBitmap(bitmap_circle_error, 0, 0, w, w, matrix, true);
    }

    // 手势绘制有误
    public void showErrorState() {
        for (GestureLockPoint point : selectedPointsList) {
            point.state = GestureLockPoint.STATE_ERROR;
        }
        invalidate();
        mHandler.postDelayed(runnable, 1000L);
    }

    // 延时消失
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            resetAndInvalidate();
        }
    };

    private Handler mHandler = new Handler();

    private OnPatterChangeListener onPatterChangeListener;

    public void setOnPatterChangeListener(OnPatterChangeListener onPatterChangeListener) {
        this.onPatterChangeListener = onPatterChangeListener;
    }

    public interface OnPatterChangeListener {

        void onPatterChanged(String password); // 绘制完成，返回绘制手势

        void onPatterStart(); // 绘制开始

    }

    public static class GestureLockPoint {

        public final static int STATE_NOR = 0; // 普通状态
        public final static int STATE_PRESS = 1; // 按下状态
        public final static int STATE_ERROR = 2; // 错误状态
        private int pointX, pointY;
        private int state;
        private int index;

        public int getPointX() {
            return pointX;
        }

        public void setPointX(int pointX) {
            this.pointX = pointX;
        }

        public int getPointY() {
            return pointY;
        }

        public void setPointY(int pointY) {
            this.pointY = pointY;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "GestureLockPoint{" +
                    "pointX=" + pointX +
                    ", pointY=" + pointY +
                    ", state=" + state +
                    ", index=" + index +
                    '}';
        }

        public GestureLockPoint() {
        }

        public GestureLockPoint(int pointX, int pointY) {
            this.pointX = pointX;
            this.pointY = pointY;

        }

        /**
         * 计算两圈圆心的距离
         *
         * @param pointA
         * @param pointB
         * @return
         */

        public static int distance(GestureLockPoint pointA, GestureLockPoint pointB) {

            return (int) Math.sqrt((double) ((pointA.pointX - pointB.pointX) * (pointA.pointX - pointB.pointX) + (pointA.pointY - pointB.pointY) * (pointA.pointY - pointB.pointY)));
        }

        /**
         * 计算两个圈的夹脚
         *
         * @param a
         * @param b
         * @return
         */
        public static float degree(GestureLockPoint a, GestureLockPoint b) {
            float degree;
            if (a.pointY == b.pointY) { // 0度、180度
                if (a.pointX > b.pointX) {
                    degree = 180;
                } else {
                    degree = 0;
                }
            } else if (a.pointX == b.pointX) { // 90度、270度
                if (a.pointY > b.pointY) {
                    degree = 270;
                } else {
                    degree = 90;
                }
            } else {
                degree = getAngle(a, b);
            }
            return degree;
        }

        public static float getAngle(GestureLockPoint a, GestureLockPoint b) {
            //两圈中心的x、y值
            int x = b.pointX - a.pointX;
            int y = b.pointY - a.pointY;
            double hypotenuse = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            //斜边长度
            double cos = x / hypotenuse;
            double radian = Math.acos(cos);
            //求出弧度
            float angle = (float) (180 / (Math.PI / radian));
            //用弧度算出角度
            if (b.pointY < a.pointY) {
                angle = 360 - angle;
            }
            return angle;
        }

    }
}
