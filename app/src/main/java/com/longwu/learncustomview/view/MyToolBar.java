package com.longwu.learncustomview.view;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longwu.learncustomview.R;


/**
 * titlebar
 */
public class MyToolBar extends Toolbar {
    TextView mCenterTitleView;//中间标题
    Context mContext;

    public MyToolBar(Context context) {
        super(context);
        initLayout(context);

    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    /**
     * 初始化
     */
    private void initLayout(Context context) {

        if (isInEditMode()) {
            return;
        }
        mContext = context;
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        mCenterTitleView = new TextView(context);
        mCenterTitleView.setId(R.id.toolbar_center_title);
        mCenterTitleView.setLayoutParams(layoutParams);
        mCenterTitleView.setText("");
        mCenterTitleView.setVisibility(View.GONE);
        setCenterTitleColor(R.color.title_text_color);
        this.addView(mCenterTitleView);
    }


    public TextView getCenterTextView() {
        return mCenterTitleView;
    }
    /**
     * 设置中间标题
     * @param text
     */
    public void setCenterTitle(@StringRes int textId) {
        mCenterTitleView.setText(mContext.getString(textId));
        mCenterTitleView.setVisibility(View.VISIBLE);
    }

    /**
     * 设置中间标题
     * @param text
     */
    public void setCenterTitle(String text) {
        mCenterTitleView.setText(text);
        mCenterTitleView.setVisibility(View.VISIBLE);
    }

    /**
     * 设置中间标题颜色
     * @param color
     */
    public void setCenterTitleColor(@ColorRes int color) {

        mCenterTitleView.setTextColor(mContext.getResources().getColor(color));
    }



}
