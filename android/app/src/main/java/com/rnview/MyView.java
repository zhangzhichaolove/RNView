package com.rnview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactImageView;
import com.facebook.react.views.text.ReactTextView;

import javax.annotation.Nullable;

public class MyView extends View {
    private String str = "这是文字啊。。。。";
    private Paint mPaint = new Paint();


    public MyView(Context context) {
        this(context, null);
        Log.e("MyView", "初始化");
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        mPaint.setTextSize(40);
        invalidate();
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        if (widthMode == MeasureSpec.UNSPECIFIED) {

        }
        if (heightMode == MeasureSpec.AT_MOST) {

        }
        //设置宽高
        setMeasuredDimension(width, height);
        setBackgroundColor(Color.YELLOW);
    }

    public void setText(String str) {
        this.str = str;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 300, 100, mPaint);
        float width = mPaint.measureText(str);
        if (width > canvas.getWidth()) {

        }
        canvas.drawText(str, 0, 200, mPaint);
        Log.e("canvas", canvas.getWidth() + "-------" + canvas.getHeight() + "----" + width);
    }
}
