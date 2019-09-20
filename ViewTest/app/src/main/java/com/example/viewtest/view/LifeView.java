package com.example.viewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LifeView extends View {
    
    private String TAG = LifeView.class.getName();

    /**
     * 构造方法：View在代码中创建时调用
     * @param context
     */
    public LifeView(Context context) {
        super(context);
    }

    /**
     * 构造方法：xml创建时调用(View从layout加载)
     * @param context
     * @param attrs
     */
    public LifeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "LifeView: ");
    }

    /**
     * 从XML文件加载完成时
     */
    @Override
    protected void onFinishInflate() {
        Log.i(TAG, "onFinishInflate: ");
        super.onFinishInflate();
    }

    /**
     * 当前View或者其祖先的可见性改变时
     * @param changedView
     * @param visibility
     */
    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        Log.i(TAG, "onVisibilityChanged: ");
        super.onVisibilityChanged(changedView, visibility);
    }

    /**
     * 当前View被附到window上时
     */
    @Override
    protected void onAttachedToWindow() {
        Log.i(TAG, "onAttachedToWindow: ");
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 当前View的尺寸发生变化
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: ");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout: ");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: ");
        super.onDraw(canvas);
    }

    /**
     * 包括当前View的window获得或者失去焦点时被调用
     * @param hasWindowFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        Log.i(TAG, "onWindowFocusChanged: ");
        super.onWindowFocusChanged(hasWindowFocus);
    }

    /**
     * 当前View从window分离时
     */
    @Override
    protected void onDetachedFromWindow() {
        Log.i(TAG, "onDetachedFromWindow: ");
        super.onDetachedFromWindow();
    }
}
