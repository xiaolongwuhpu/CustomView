package com.longwu.learncustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.longwu.learncustomview.R;
import com.longwu.learncustomview.util.DensityUtils;
import com.longwu.learncustomview.util.ScreenUtil;

import java.lang.reflect.TypeVariable;
import java.util.Random;

/**
 * Created by wujing on 2017/4/20 0020.
 */

public class SimpleView extends View {

    public String getmTitleText() {
        return mTitleText;
    }

    public void setmTitleText(String mTitleText) {
        this.mTitleText = mTitleText;
        postInvalidate();
    }

    String mTitleText = "0";
    int mTitleTextColor;
    int mTitleTextSize;
    private Bitmap mimage;
    int mImageScale ;
    Paint mPaint;
    Rect mRect,mRect1,mRect2,mRect3,mRect4;

    public SimpleView(Context context) {
        this(context, null);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    Context mcontext;

    public SimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int cont = a.getIndexCount();
        for (int i = 0; i < cont; i++) {
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
                case R.styleable.CustomTitleView_image:
                    mimage = BitmapFactory.decodeResource(getResources(),a.getResourceId(attr,0)) ;
                    break;
                case R.styleable.CustomTitleView_imageScaleType:
                    mImageScale = a.getInt(attr, 0);
                    break;
            }
        }
        a.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setColor(mTitleTextColor);
        mRect = new Rect();
        mRect1 = new Rect();
        mRect2 = new Rect();
        mRect3 = new Rect();
        mRect4 = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
//        mwidth =  (getPaddingLeft()+mRect.width()+getPaddingRight())/2;
//        mheight =  (getTop()+mRect.height()+getBottom())/2;
    }
//int mwidth = 0;
//int mheight = 0;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(DensityUtils.dip2px(mcontext,500), widthMeasureSpec);
        int height = getMySize(DensityUtils.dip2px(mcontext,500), heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

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

    int wh = 200;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.argb(100,80,80,80));
        int mDaoHangHeight = ScreenUtil.getDaoHangHeight(mcontext)+ScreenUtil.getStatusBarHeight(mcontext)/2;
        canvas.drawRect(0,mDaoHangHeight,getWidth()/2,getHeight()/2+mDaoHangHeight,mPaint);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, mDaoHangHeight, getWidth()/2-wh, getHeight()/2-wh+mDaoHangHeight, mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setFakeBoldText(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(50);
        canvas.drawText("0", getWidth()/4-wh/2-mRect.width()*1.0f/2, getHeight()/4-wh/2+mDaoHangHeight- getPaddingBottom(), mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(getWidth()/2-wh, getHeight()/2-wh+mDaoHangHeight, getWidth()/2, getHeight()/2+mDaoHangHeight, mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(45);
        canvas.drawText("1", getWidth()/2-wh/2-mRect.width()*1.0f/2, getHeight()/2-wh/2+mDaoHangHeight-getPaddingBottom(), mPaint);


        mPaint.setColor(Color.RED);
        canvas.drawRect(0, getHeight()/2-wh+mDaoHangHeight, wh, getHeight()/2+mDaoHangHeight, mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(45);
        canvas.drawText("2", wh/2-mRect.width()*1.0f/2, getHeight()/2-wh/2+mDaoHangHeight- getPaddingBottom(), mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawRect(getWidth()/2-wh, mDaoHangHeight, getWidth()/2, wh+mDaoHangHeight, mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(45);
        canvas.drawText("3", getWidth()/2-wh/2-mRect.width()*1.0f/2, wh/2+mDaoHangHeight- getPaddingBottom(), mPaint);



    }

}
