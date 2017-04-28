package com.longwu.learncustomview.view.shaderview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.longwu.learncustomview.R;

/**
 * Created by wujing on 2017/4/26 0026.
 * 环形渐变
 */

public class RadialGradientView extends View {

    Paint mpaint;
    RadialGradient mRadialGradient;
    int mPaintstyle;
    Rect mRect;

    public RadialGradientView(Context context) {
        this(context, null);
    }


    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int con = a.getIndexCount();
        for (int i = 0; i < con; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_pstyle:
                    mPaintstyle = a.getInt(attr, 0);
                    break;
            }
        }
        a.recycle();
        init();
    }

    int mTileMode = 0;
    String t = "";

    private void init() {
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mpaint.setStyle(Paint.Style.STROKE);
//        mpaint.setStrokeWidth(10);
        if (mPaintstyle == 0) {
            mRadialGradient = new RadialGradient(200, 200, 150, new int[]{Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN}, null/*new float[]{0, .2F,
                    .6F, .9F}*/, Shader.TileMode.CLAMP);
            mTileMode = 0;
            t = "Shader.TileMode.CLAMP";
        } else if (mPaintstyle == 1) {
            mRadialGradient = new RadialGradient(200, 200, 150, new int[]{Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN}, null/*new float[]{0, .2F,
                    .6F, .9F}*/, Shader.TileMode.MIRROR);
            mTileMode = 1;
            t = "Shader.TileMode.MIRROR";
        } else if(mPaintstyle == 2){
            mRadialGradient = new RadialGradient(200, 200, 150, new int[]{Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN},null /*new float[]{0, .2F,
                    .6F, .9F}*/, Shader.TileMode.REPEAT);
            mTileMode = 2;
            t = "Shader.TileMode.REPEAT";
        }else
        if (mPaintstyle == 3) {
            mRadialGradient = new RadialGradient(200, 200, 150, new int[]{Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN}, null/*new float[]{0, .2F,
                    .6F, .9F}*/, Shader.TileMode.CLAMP);
            mTileMode = 3;
            t = "Shader.TileMode.CLAMP";
        } else if (mPaintstyle == 4) {
            mRadialGradient = new RadialGradient(200, 200, 150, new int[]{Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN}, null/*new float[]{0, .2F,
                    .6F, .9F}*/, Shader.TileMode.MIRROR);
            mTileMode = 4;
            t = "Shader.TileMode.MIRROR";
        } else {
            mRadialGradient = new RadialGradient(200, 200, 150, new int[]{Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN},null /*new float[]{0, .2F,
                    .6F, .9F}*/, Shader.TileMode.REPEAT);
            mTileMode = 5;
            t = "Shader.TileMode.REPEAT";
        }
        mRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(200, widthMeasureSpec);
        int height = getMySize(200, heightMeasureSpec);
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
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它,  match_parent
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    int textHeight;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mpaint.setShader(mRadialGradient);
        if(mTileMode <3){
            canvas.drawCircle(200, 200, 150, mpaint);
        }else{
            canvas.drawRect(0, 0, getWidth()*3/7,getHeight(), mpaint);
        }

        mpaint.reset();

        mpaint.setTextSize(40);
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setFakeBoldText(true);
        mpaint.setTypeface(Typeface.DEFAULT_BOLD);
        mpaint.getTextBounds(t, 0, t.length(), mRect);
        textHeight = mRect.height() + getPaddingBottom() + getPaddingTop();
        canvas.drawText(t, 460, 200 + textHeight / 2, mpaint);
    }
}
