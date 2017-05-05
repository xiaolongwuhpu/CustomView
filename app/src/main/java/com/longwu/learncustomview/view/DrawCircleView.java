package com.longwu.learncustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.longwu.learncustomview.R;
import com.longwu.learncustomview.util.DensityUtils;

/**
 * Created by wujing on 2017/4/26 0026.
 */

public class DrawCircleView extends View {

    Paint mpaint;
    SweepGradient mSweepGradient;
    Rect mRect;
    Context mcontext;
    RectF oval;

    public DrawCircleView(Context context) {
        this(context, null);
    }


    public DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public int getmMax() {
        return mMax;
    }

    public void setmMax(int mMax) {
        this.mMax = mMax;
        invalidate();
//        postInvalidate();

    }

    public float getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public float getRadiusWidth() {
        return RadiusWidth;
    }

    public void setRadiusWidth(float radiusWidth) {
        RadiusWidth = radiusWidth;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int[] getColors() {
        return new int[]{firstcolor,secondcolor,thirdcolor,fourthcolor};
    }

    public void setColors(int firstcolor, int secondcolor, int thirdcolor, int fourthcolor) {
        this.firstcolor = firstcolor;
        this.secondcolor = secondcolor;
        this.thirdcolor = thirdcolor;
        this.fourthcolor = fourthcolor;
        invalidate();
    }

    private int mMax = 120;
    private float mTextSize = 35;
    private float RadiusWidth = 0;
    private boolean textIsDisplayable;
    private int roundProgressColor, textColor, style;
private  int firstcolor,secondcolor,thirdcolor,fourthcolor;
    public DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress, defStyleAttr, 0);

        mMax = a.getInt(R.styleable.RoundProgress_max, 200);
        mTextSize = a.getDimension(R.styleable.RoundProgress_textSize, 45);
        roundProgressColor = a.getColor(R.styleable.RoundProgress_roundProgressColor, Color.GREEN);
        textColor = a.getColor(R.styleable.RoundProgress_textColor, Color.BLACK);
        firstcolor = a.getColor(R.styleable.RoundProgress_firstColor, Color.BLUE);
        secondcolor = a.getColor(R.styleable.RoundProgress_secondColor, Color.RED);
        thirdcolor = a.getColor(R.styleable.RoundProgress_thirdColor, Color.YELLOW);
        fourthcolor = a.getColor(R.styleable.RoundProgress_fourthColor, Color.GREEN);
        RadiusWidth = a.getDimension(R.styleable.RoundProgress_radiuswidth, 200) / 2;
        textIsDisplayable = a.getBoolean(R.styleable.RoundProgress_textIsDisplayable, true);
        style = a.getInt(R.styleable.RoundProgress_style, 0);
        a.recycle();
        init();
    }

    String t = "";

    private void init() {
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint.setStrokeWidth(15);
        mpaint.setColor(Color.argb(50, 80, 80, 80));
        mpaint.setStyle(Paint.Style.STROKE);
        t = (int) (mMax * 1.0f / 360 * 100) + "%";
        mRect = new Rect();
//        mSweepGradient = new SweepGradient(RadiusWidth, RadiusWidth, new int[]{firstcolor, secondcolor, thirdcolor, fourthcolor}, null/*new float[] { 0, .2F,
//                .6F, .9F }*/);
    }
    int textHeight = 0;
    int textWidth = 0;
    private int Viewwidth = 200;
    private int Viewheight = 200;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Viewwidth = getMySize(DensityUtils.dip2px(mcontext, 200), widthMeasureSpec);
        Viewheight = getMySize(DensityUtils.dip2px(mcontext, 200), heightMeasureSpec);

//        if (Viewwidth < Viewheight) {
//            RadiusWidth = Viewwidth/2;
//        } else {
//            RadiusWidth = Viewheight/2;
//        }
        setMeasuredDimension(Viewwidth, Viewheight);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size  ,wrap_content
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它,  match_parent
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mSweepGradient = new SweepGradient(RadiusWidth, RadiusWidth, new int[]{firstcolor, secondcolor, thirdcolor, fourthcolor,firstcolor}, new float[] { 0, .2F,
                .6F, .8F ,.9F});
        t = (int) (mMax * 1.0f / 360 * 100) + "%";
        mpaint.setStrokeWidth(15);
        mpaint.setColor(Color.argb(50, 80, 80, 80));
        canvas.drawCircle(RadiusWidth, RadiusWidth, RadiusWidth - 10, mpaint);

        mpaint.reset();
        mpaint.setStrokeWidth(15);
        mpaint.setAntiAlias(true);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setShader(mSweepGradient);
        oval = new RectF(10, 10, 2 * RadiusWidth - 10, 2 * RadiusWidth - 10);
        mpaint.setStrokeCap(Paint.Cap.ROUND); //圆形 ,不设置也可以
        canvas.drawArc(oval, -90, mMax, false, mpaint); //// 根据进度画圆弧 矩形内切圆
//        canvas.drawArc(50,50,200,200,-90,mMax,false,mpaint);

        mpaint.reset();
        mpaint.setTextSize(mTextSize);
        mpaint.setColor(textColor);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setFakeBoldText(true);
        mpaint.setTypeface(Typeface.DEFAULT_BOLD);
        mpaint.getTextBounds(t, 0, t.length(), mRect);
        textHeight = mRect.height() + getPaddingBottom() + getPaddingTop();
        textWidth = mRect.width() + getPaddingLeft() + getPaddingRight();
        canvas.drawText(t, RadiusWidth - textWidth / 2, RadiusWidth + textHeight / 2, mpaint);
    }


}
