package com.example.drawabletest.test;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.style.LineHeightSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestDrawable extends Drawable {

    private Paint mPaint;

    // 半径
    float radius = 200;

    public TestDrawable(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setARGB(100, 244, 92, 71);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        // 画圆
        PointF point = new PointF(radius, radius);
        canvas.drawCircle(point.x, point.y, radius, mPaint);

        // 画正三角
        PointF trianglePoint1 = new PointF(radius, radius*2);
        PointF trianglePoint2 = calculatePoint(trianglePoint1, radius*2, -60);
        PointF trianglePoint3 = calculatePoint(trianglePoint1, radius*2, -120);
        Path mPath = new Path();
        mPath.reset();
        mPath.moveTo(trianglePoint1.x, trianglePoint1.y);
        mPath.lineTo(trianglePoint2.x, trianglePoint2.y);
        mPath.lineTo(trianglePoint3.x, trianglePoint3.y);
        canvas.drawPath(mPath, mPaint);

        // 画梯形
        PointF trapezoidTopMiddlePoint = new PointF(radius*4, radius/2);
        PointF trapezoidPoint1 = calculatePoint(trapezoidTopMiddlePoint, radius/2, 0);
        PointF trapezoidPoint2 = calculatePoint(trapezoidTopMiddlePoint, radius/2, -180);
        PointF trapezoidBottomMiddlePoint = new PointF(radius*4, radius*1.6F);
        PointF trapezoidPoint3 = calculatePoint(trapezoidBottomMiddlePoint, radius, 0);
        PointF trapezoidPoint4 = calculatePoint(trapezoidBottomMiddlePoint, radius, -180);
        mPath.reset();
        mPath.moveTo(trapezoidPoint1.x, trapezoidPoint1.y);
        mPath.lineTo(trapezoidPoint2.x, trapezoidPoint2.y);
        mPath.lineTo(trapezoidPoint4.x, trapezoidPoint4.y);
        mPath.lineTo(trapezoidPoint3.x, trapezoidPoint3.y);
        canvas.drawPath(mPath, mPaint);

        // 二阶贝塞尔曲线
        PointF bezierStartPoint = new PointF(radius*2, radius*2);
        PointF bezierEndPoint = new PointF(radius*4, radius*2);
        PointF controlPoint = new PointF(radius*6f, radius*6f);
        mPath.reset();
        mPath.moveTo(bezierStartPoint.x, bezierStartPoint.y);
        mPath.quadTo(controlPoint.x, controlPoint.y, bezierEndPoint.x, bezierEndPoint.y);
        canvas.drawPath(mPath, mPaint);

    }

    public PointF calculatePoint(PointF point, float length, float angle){
        float x = (float) (Math.cos(Math.toRadians(angle)) * length);
        float y = (float) (Math.sin(Math.toRadians(angle-180)) * length);
        return new PointF(point.x+x, point.y + y);
    }


    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
//        return super.getIntrinsicHeight();
        return (int)(6 * radius);
    }


    @Override
    public int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }
}
