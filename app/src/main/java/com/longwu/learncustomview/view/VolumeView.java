package com.longwu.learncustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.longwu.learncustomview.R;
import com.longwu.learncustomview.util.DensityUtils;

/**
 * Created by wujing on 2017/5/5
 */

public class VolumeView extends View {

    Paint mpaint;
    Rect mRect;
    Context mcontext;
    RectF oval;

    public VolumeView(Context context) {
        this(context, null);
    }


    public VolumeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    private float RadiusWidth = 0;
    private float circleWidth = 0;
    private int firstcolor, secondcolor;
    int dotCount, splitSize;
//    private  Drawable bg;

    /**
     * 当前进度
     */
    private int mCurrentCount = 3;

    /**
     * 中间的图片
     */
    private Bitmap mImage;
    /**
     * 每个块块间的间隙
     */
    private int mSplitSize;
    /**
     * 个数
     */
    private int mCount;

    public VolumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomVolume, defStyleAttr, 0);

        firstcolor = a.getColor(R.styleable.CustomVolume_VolumefirstColor, Color.BLUE);
        secondcolor = a.getColor(R.styleable.CustomVolume_VolumesecondColor, Color.RED);
        mCount = a.getInt(R.styleable.CustomVolume_dotCount, 20);
        mSplitSize = a.getInt(R.styleable.CustomVolume_splitSize, 20);
//        RadiusWidth = a.getDimension(R.styleable.CustomVolume_circleWidth, 120);
        circleWidth = a.getDimensionPixelSize(R.styleable.CustomVolume_circleWidth, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
//        bg = a.getDrawable(R.styleable.CustomVolume_bg);
        mImage = BitmapFactory.decodeResource(getResources(),R.styleable.CustomVolume_bg);
        a.recycle();
        init();
    }

    String t = "";
    private void init() {
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint.setStrokeWidth(15);
        mpaint.setColor(Color.argb(50, 80, 80, 80));
        mpaint.setStyle(Paint.Style.STROKE);
        mRect = new Rect();
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

        if (Viewwidth < Viewheight) {
            RadiusWidth = Viewwidth/2;
        } else {
            RadiusWidth = Viewheight/2;
        }
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
        mpaint.setStrokeWidth(circleWidth);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
        mpaint.setAntiAlias(true);
        mpaint.setDither(true);  //防抖动,显示效果更好
        float  center  = RadiusWidth;
        float radius = center-circleWidth/2;
        drawrect(canvas,center,radius);
//        canvas.drawCircle(RadiusWidth, RadiusWidth, RadiusWidth - 10, mpaint);

//        mpaint.reset();
//        mpaint.setStrokeWidth(15);
//        mpaint.setAntiAlias(true);
//        mpaint.setStyle(Paint.Style.STROKE);
//        oval = new RectF(10, 10, 2 * RadiusWidth - 10, 2 * RadiusWidth - 10);
//        mpaint.setStrokeCap(Paint.Cap.ROUND); //圆形 ,不设置也可以
////        canvas.drawArc(oval, -90, mMax, false, mpaint); //// 根据进度画圆弧 矩形内切圆

//        mpaint.reset();
//        mpaint.setTextSize(mTextSize);
//        mpaint.setColor(textColor);
//        mpaint.setStyle(Paint.Style.STROKE);
//        mpaint.setFakeBoldText(true);
//        mpaint.setTypeface(Typeface.DEFAULT_BOLD);
//        mpaint.getTextBounds(t, 0, t.length(), mRect);
//        textHeight = mRect.height() + getPaddingBottom() + getPaddingTop();
//        textWidth = mRect.width() + getPaddingLeft() + getPaddingRight();
//        canvas.drawText(t, RadiusWidth - textWidth / 2, RadiusWidth + textHeight / 2, mpaint);
    }

    private void drawrect(Canvas canvas, float center, float radius) {

        /**
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360
         */
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;
        RectF rectf = new RectF(center-radius,center-radius,center+radius,center+radius);// 用于定义的圆弧的形状和大小的界限

        mpaint.setColor(firstcolor);
        for(int i = 0;i<mCount;i++){
            canvas.drawArc(rectf,i*(mSplitSize+itemSize),itemSize,false,mpaint);
        }

        mpaint.setColor(secondcolor); // 设置圆环的颜色
        for (int i = 0; i < mCurrentCount; i++)
        {
            canvas.drawArc(rectf, i * (itemSize + mSplitSize), itemSize, false, mpaint); // 根据进度画圆弧
        }
    }


}
