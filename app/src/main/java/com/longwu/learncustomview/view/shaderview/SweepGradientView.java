package com.longwu.learncustomview.view.shaderview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.longwu.learncustomview.R;
import com.longwu.learncustomview.util.DensityUtils;

/**
 * Created by wujing on 2017/4/26 0026.
 * 扫描/梯度渲染
 */

public class SweepGradientView extends View {

    Paint mpaint;
    SweepGradient mSweepGradient;
    String mTitleText;
    int mTitleTextColor;
    int mTitleTextSize;
    int mPaintstyle;
    Rect mRect;
    Context mcontext;
    public SweepGradientView(Context context) {
//        super(context);
        this(context, null);
    }


    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int con = a.getIndexCount();
        for (int i = 0; i < con; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    mTitleTextColor = a.getColor(attr, defStyleAttr);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
//                case R.styleable.CustomTitleView_paintstyle:
//                    mPaintstyle = a.getInt(attr, 0);
//                    break;
                case R.styleable.CustomTitleView_pstyle:
                    mPaintstyle = a.getInt(attr, 0);
                    break;
            }
        }
        a.recycle();
        init();
    }

    String t = "";

    private void init() {
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (mPaintstyle == 0) {
            mpaint.setStrokeWidth(20);
            mpaint.setStyle(Paint.Style.STROKE);
            t = "Style.STROKE";
        } else {
            mpaint.setStyle(Paint.Style.FILL);
            t = "Style.FILL";
        }
        mRect = new Rect();


        mSweepGradient = new SweepGradient(200, 200, new int[]{Color.YELLOW,
                Color.RED, Color.BLUE, Color.GREEN}, null/*new float[] { 0, .2F,
                .6F, .9F }*/);
    }

    int textHeight = 0;
    int textWidth = 0;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(DensityUtils.dip2px(mcontext,220), widthMeasureSpec);
        int height = getMySize(DensityUtils.dip2px(mcontext,220), heightMeasureSpec);

//        if (width < height) {
//            height = width;
//        } else {
//            width = height;
//        }

        setMeasuredDimension(width, height);
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
                //我们将大小取最大值,你也可以取其他值
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
//        super.onDraw(canvas);
        mpaint.setShader(mSweepGradient);
        canvas.drawCircle(200, 200, 156, mpaint);

        mpaint.reset();

        mpaint.setTextSize(40);
        mpaint.setColor(Color.RED);
//        mpaint.setStrokeWidth(25);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setFakeBoldText(true);
        mpaint.setTypeface(Typeface.DEFAULT_BOLD);
        mpaint.getTextBounds(t, 0, t.length(), mRect);
        textHeight= mRect.height()+getPaddingBottom()+getPaddingTop();
        canvas.drawText(t, 370, 200+textHeight/2, mpaint);
    }
}
