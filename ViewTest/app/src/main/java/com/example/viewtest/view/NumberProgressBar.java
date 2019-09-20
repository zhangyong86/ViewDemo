package com.example.viewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class NumberProgressBar extends View {

    private String TAG = NumberProgressBar.class.getName();

    //当前进度
    private float mCurrentProgress = 20;

    //最大进度值
    private float mMaxProgress = 100;

    //文本-当前进度展示
    private String mText = "20%";

    //进度条高度
    private float mProgressBarHeight = 10;

    //左半部画笔
    private Paint mReachedBarPaint;

    //右半部画笔
    private Paint mUnReachedBarPaint;

    //文本画笔
    private Paint mTextPaint;

    //左半部矩形
    private RectF mReachedRectF = new RectF(0, 0, 0, 0);

    //右半部矩形(参数float)
    private RectF mUnreachedRectF = new RectF(0, 0, 0, 0);

    //文本宽度
    private float mDrawTextWidth;

    //文本起始点-drawText
    private float mDrawTextStart;

    //文本baseLine坐标点-drawText
    private float mDrawTextEnd;

    public NumberProgressBar(Context context) {
        this(context, null);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    /**
     *  初始化所有画笔
     */
    private void initPaints(){
        mReachedBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mReachedBarPaint.setColor(Color.RED);

        mUnReachedBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnReachedBarPaint.setColor(Color.BLUE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(40);
    }


    /**
     * 测量View的宽高
     * 如果自定义View用到wrap_content,需要重写onMeasure
     * xml声明为wrap_content时: 参数-widthMeasureSpec:size是父布局的大小;mode是at_most
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth){
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result;
        if (mode == MeasureSpec.AT_MOST){//wrap_content
            result = isWidth ? getSuggestedMinimumWidth(): getSuggestedMinimumHeight();
            int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
            result = result + padding;
            if (isWidth)
                result = Math.max(result, size);//长度取较大值：保证长度
            else
                result = Math.min(result, size);//高度取最小值：防止占用无用的高度(存在占用父布局的全部高度)
        }else {
            result = size;
        }
        return result;
    }

    /**
     * 绘图：绘图坐标系和canvas坐标系
     * 坐标原点是在View的左上角(canvas坐标系可能变化)
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i(TAG, "onDraw:width: " + getWidth());//1080
//        Log.i(TAG, "onDraw:height: " + getHeight());//1572
        calculateDrawRectF();
        canvas.drawRect(mReachedRectF, mReachedBarPaint);
        canvas.drawText(mText, mDrawTextStart, mDrawTextEnd, mTextPaint);
        canvas.drawRect(mUnreachedRectF, mUnReachedBarPaint);
    }

    /**
     * 测量左右半部矩形的位置、大小
     */
    private void calculateDrawRectF(){

        mText = String.format("%d", (int)getProgress() * 100 / (int)mMaxProgress) + "%";

        //左半部
        mReachedRectF.left = getPaddingLeft();//抛除padding的大小
        mReachedRectF.top = getHeight() / 2.0f - mProgressBarHeight / 2.0f;
        mReachedRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) * (mCurrentProgress / mMaxProgress) + getPaddingLeft();
        mReachedRectF.bottom = getHeight() / 2.0f + mProgressBarHeight / 2.0f;

        //文本
        mDrawTextWidth = mTextPaint.measureText(mText);
        mDrawTextStart = mReachedRectF.right;
        mDrawTextEnd = (int) ((getHeight() / 2.0f) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2.0f));//baseLine

        //右半部
        mUnreachedRectF.left = mReachedRectF.right + mDrawTextWidth;
        mUnreachedRectF.top = getHeight() / 2.0f - mProgressBarHeight / 2.0f;
        mUnreachedRectF.right = getWidth() - getPaddingRight();
        mUnreachedRectF.bottom = getHeight() / 2.0f + mProgressBarHeight / 2.0f;
    }

    public void setProgress(float progress){
        if (progress <= mMaxProgress && progress >= 0) {
            mCurrentProgress = progress;
            invalidate();
        }
    }

    public float getProgress(){
        return mCurrentProgress;
    }
}
