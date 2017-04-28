package com.longwu.learncustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.longwu.learncustomview.R;

import java.util.Random;

/**
 * Created by wujing on 2017/4/20 0020.
 */

public class CanClickView extends View {

    public String getmTitleText() {
        return mTitleText;
    }

    public void setmTitleText(String mTitleText) {
        this.mTitleText = mTitleText;
        postInvalidate();
    }

    String mTitleText;
    int mTitleTextColor;
    int mTitleTextSize;
    private Bitmap mimage;
    int mImageScale ;
    Paint mPaint;
    Rect mRect;

    public CanClickView(Context context) {
        this(context, null);
    }

    public CanClickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CanClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = getRandomText();
                Log.e("111","view"+a);
                mTitleText = a;
                postInvalidate();
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 200;
        int height = 100;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
            int textWidth = mRect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
            int textHeight = mRect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawText(mTitleText, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
    }


    private String getRandomText() {
        Random random = new Random();
        int a = 1000;
        while (true) {
            a = random.nextInt(9999);
            if (a > 999) {
                break;
            }
        }
        return "" + a;
    }
}
