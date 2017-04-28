package com.longwu.learncustomview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.longwu.learncustomview.BaseActivity;
import com.longwu.learncustomview.R;
import com.longwu.learncustomview.activity.shader.BitmapShaderActivity;
import com.longwu.learncustomview.activity.shader.ComposeShaderActivity;
import com.longwu.learncustomview.activity.shader.RadialGradientViewActivity;
import com.longwu.learncustomview.activity.shader.SweepGradientViewActivity;

import butterknife.BindView;

/**
 * Created by wujing on 2017/4/27 0027.
 */

public class PaintSetShader extends BaseActivity {

    @BindView(R.id.sweepgradent)
    Button sweepgradent;
    @BindView(R.id.radialgradient)
    Button radialgradient;
    @BindView(R.id.composeshader)
    Button composeshader;
    @BindView(R.id.bitmapshader)
    Button bitmapshader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_paint_setshader);

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
        mCommonToolbar.setCenterTitle(" Paint.setShader");
    }

    @Override
    public void initStatusBar() {

    }

    @Override
    public void initView() {
        sweepgradent.setText("sweepgradent扫描/梯度渲染");
        radialgradient.setText("radialgradient环形渐变");
        composeshader.setText("composeshader多图组合");
        bitmapshader.setText("bitmapshader");
    }

    public void sweepgradent(View view){
        Intent it = new Intent(PaintSetShader.this,SweepGradientViewActivity.class);
        startActivity(it);
    }
    public void radialgradient(View view){
        Intent it = new Intent(PaintSetShader.this, RadialGradientViewActivity.class);
        startActivity(it);
    }
    public void ComposeShader(View view){
        Intent it = new Intent(PaintSetShader.this, ComposeShaderActivity.class);
        startActivity(it);
    }
    public void bitmapshader(View view){
        Intent it = new Intent(PaintSetShader.this, BitmapShaderActivity.class);
        startActivity(it);
    }
}
