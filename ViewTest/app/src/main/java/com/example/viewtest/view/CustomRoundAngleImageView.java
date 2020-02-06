package com.example.viewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 使用裁剪实现圆角
 * 基本原理：使用Path绘制一个圆角矩形，最后通过clipPath裁剪出来
 */
public class CustomRoundAngleImageView extends AppCompatImageView {
    float width, height;
    float corner = 15;//圆角


    public CustomRoundAngleImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    /**
     * Canvas坐标系有且只有一个，且是唯一不变的，其坐标原点在View的左上角
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (width >= 12 && height > 12) {
            Path path = new Path();
            //四个圆角
            path.moveTo(corner, 0);//移动画笔
            path.lineTo(width - corner, 0);//画直线-边框
            path.quadTo(width, 0, width, corner);//画二阶贝塞尔曲线-圆角
            path.lineTo(width, height - corner);
            path.quadTo(width, height, width - corner, height);
            path.lineTo(corner, height);
            path.quadTo(0, height, 0, height - corner);
            path.lineTo(0, corner);
            path.quadTo(0, 0, corner, 0);

            canvas.clipPath(path);//根据path裁剪画布
        }
        super.onDraw(canvas);
    }
}
