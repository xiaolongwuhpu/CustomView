package com.longwu.learncustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.longwu.learncustomview.R;

/**
 * Created by wujing on 2017/4/20 0020.
 */

public class PicAndTextSimpleView extends View {

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
    int mImageScale;
    Paint mPaint;
    Rect mRect;
    Rect mTextBound;
    int mWidth, mHeight;

    public PicAndTextSimpleView(Context context) {
        this(context, null);
    }

    public PicAndTextSimpleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PicAndTextSimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int cont = a.getIndexCount();
        for (int i = 0; i < cont; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_image:
                    mimage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomTitleView_imageScaleType:
                    mImageScale = a.getInt(attr, 0);
                    break;
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    mTitleTextColor = a.getColor(attr, defStyleAttr);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        init();
    }

    private void init() {
        mRect = new Rect();
        mTextBound = new Rect();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //width
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {//match_parent
            mWidth = widthSize;
            Log.e("xxx", "EXACTLY:" + mWidth);
        } else {
            int desireByImg = getPaddingLeft() + getPaddingRight() + mimage.getWidth();
            int desireByTitle = mTextBound.width() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) { //wrap_content
                int desire = Math.max(desireByImg, desireByTitle);
                mWidth = Math.min(desire, widthSize);
                Log.e("xxx", "AT_MOST:" + mWidth);
            }
        }

        //height
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {//match_parent
            mHeight = heightSize;
            Log.e("xxx", "EXACTLY:" + mHeight);
        } else {
            int desire = mimage.getHeight() + getPaddingTop() + getPaddingBottom()+mTextBound.height();
            if (heightMode == MeasureSpec.AT_MOST) { //wrap_content
                mHeight = Math.min(desire, heightSize);
                Log.e("xxx", "AT_MOST:" + mHeight);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
     //   super.onDraw(canvas);
        mPaint.setColor(Color.CYAN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if (mTextBound.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else {
            //正常情况，将字体居中
            canvas.drawText(mTitleText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }

        //取消使用掉的快
        mRect.bottom -= mTextBound.height();

        if (mImageScale == mImageScale) {
            canvas.drawBitmap(mimage, null, mRect, mPaint);
        } else {
            //计算居中的矩形范围
            mRect.left = mWidth / 2 - mimage.getWidth() / 2;
            mRect.right = mWidth / 2 + mimage.getWidth() / 2;
            mRect.top = (mHeight - mTextBound.height()) / 2 - mimage.getHeight() / 2;
            mRect.bottom = (mHeight - mTextBound.height()) / 2 + mimage.getHeight() / 2;

            canvas.drawBitmap(mimage, null, mRect, mPaint);
        }
    }
}
