package com.example.viewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.viewtest.R;


/**
 * 使用xfermode实现圆角
 * 基本原理：绘制一个圆角矩形-drawRoundRect,再绘制bitmap，使用xfermode-SRC_IN实现叠加后取交集,最后绘制出来交集
 */
public class RoundConerImage extends AppCompatImageView {

    /**
     * 图片
     */
    private Bitmap mSrc;

    /**
     * 圆角的大小
     */
    private int mRadius;

    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;


    public RoundConerImage(Context context) {
        super(context);
        init();
    }

    public RoundConerImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundConerImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        mRadius = 15;//设置圆角
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = getWidthSize(widthMeasureSpec);
        mHeight = getHeightSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    private int getWidthSize(int measureSpec){
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.AT_MOST){
            return  mSrc.getWidth();
        }else {
            return size;
        }
    }

    private int getHeightSize(int measureSpec){
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.AT_MOST){
            return  mSrc.getHeight();
        }else {
            return size;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = createRoundConerImage(mSrc);//获取交集的bitmap
        canvas.drawBitmap(bitmap, 0, 0, null);//绘制到最后的画板上
//        super.onDraw(canvas);
    }

    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN，参考上面的说明
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 根据原图添加圆角
     *
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);//绘制圆角矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//两个绘制的效果叠加后取交集
        canvas.drawBitmap(source, 0, 0, paint);//绘制图片
        return target;
    }

}
