package com.longwu.learncustomview.activity.shader;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.longwu.learncustomview.BaseActivity;
import com.longwu.learncustomview.R;

/**
 * Created by wujing on 2017/4/26 0026.
 */

public class SweepGradientViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sweepgradient);

    }
    @Override
    public void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.mipmap.btn_back);
        mCommonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCommonToolbar.setCenterTitle("SweepGradient扫描/梯度渲染");
    }

    @Override
    public void initStatusBar() {

    }

    @Override
    public void initView() {

    }
}
