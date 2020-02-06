package com.example.viewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class ProgressView extends View {

    private static final String TAG = ProgressView.class.getSimpleName();

    private Paint backgroundPaint;
    private Rect backgroundRect;
    private Paint foregroundPaint;
    private Rect foregroundRect;

    public ProgressView(Context context) {
        super(context);
        initPaint();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.RED);
        backgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        foregroundPaint = new Paint();
        foregroundPaint.setColor(Color.BLUE);
        foregroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        foregroundPaint.setAntiAlias(true);//设置抗锯齿
    }

    /**
     * left、top、right、bottom分别表示相对于parent的left, top, right, bottom position
     * getWidth = right - left;
     * getHeight = bottom - top;
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        backgroundRect = new Rect(left, top, right, bottom);
        Log.i(TAG, "onLayout: " + left + " " + top + " " + right + " " + bottom);
        backgroundRect = new Rect(0, 0, getWidth(), getHeight());
        foregroundRect = new Rect(0, 0, getWidth()/ 2, getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: " + backgroundRect + backgroundPaint);

        canvas.drawRect(backgroundRect, backgroundPaint);
//        canvas.drawRect(foregroundRect, foregroundPaint);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(getWidth()/2, 0);
        path.quadTo(getWidth()/2 + getHeight()/2, getHeight()/2, getWidth()/2, getHeight());
        path.lineTo(0, getHeight());
//        path.lineTo(0, 0);
        path.close();
        canvas.drawPath(path, foregroundPaint);

        super.onDraw(canvas);
    }
}
