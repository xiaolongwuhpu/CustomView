package com.longwu.learncustomview;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.longwu.learncustomview.activity.CanClickViewActivity;
import com.longwu.learncustomview.activity.DrawCircleActivity;
import com.longwu.learncustomview.activity.PaintSetShader;
import com.longwu.learncustomview.activity.PicAndTextViewActivity;
import com.longwu.learncustomview.activity.SimpleViewActivity;
import com.longwu.learncustomview.network.NetConnectionObserver;
import com.longwu.learncustomview.view.SimpleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NetConnectionObserver {
    String TAG = "test";
    private ContentResolver cr;
    private List<Map<String, Object>> lm = new ArrayList<>();//
    SimpleView sv;

    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        sv = (SimpleView) findViewById(R.id.sv);
        /**省略一些方法**/
        Myapplication.getInstance().addNetObserver(this);

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
        mCommonToolbar.setCenterTitle("自定义View研究");
    }

    @Override
    public void initStatusBar() {
    }

    @Override
    public void initView() {
        button4.setText("Paint.SetShader()");
        button5.setText("圆环");
    }

    public void button1(View view){
        Intent it = new Intent(MainActivity.this,SimpleViewActivity.class);
        startActivity(it);
    }
    public void button2(View view){
        Intent it = new Intent(MainActivity.this,CanClickViewActivity.class);
        startActivity(it);
    }
    public void button3(View view){
        Intent it = new Intent(MainActivity.this, PicAndTextViewActivity.class);
        startActivity(it);
    }
    public void button4(View view){
        Intent it = new Intent(MainActivity.this, PaintSetShader.class);
        startActivity(it);
    }
    public void button5(View view){
        Intent it = new Intent(MainActivity.this, DrawCircleActivity.class);
        startActivity(it);
    }



    @Override
    public void updateNetStatus(int type) {
        Toast.makeText(this,"网络类型是:" + type,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Myapplication.getInstance().removeNetObserver(this);
    }

    //获得手机的宽度和高度像素单位为px
// 通过WindowManager获取
    public void getScreenDensity_ByWindowManager() {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + width + "x" + height + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);
    }

    // 通过Resources获取
    public void getScreenDensity_ByResources() {
        DisplayMetrics mDisplayMetrics = getResources().getDisplayMetrics();
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + width + "x" + height + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);

    }

    // 获取屏幕的默认分辨率
    public void getDefaultScreenDensity() {
        Display mDisplay = getWindowManager().getDefaultDisplay();
        int width = mDisplay.getWidth();
        int height = mDisplay.getHeight();
        Log.d(TAG, "Screen Default Ratio: [" + width + "x" + height + "]");
        Log.d(TAG, "Screen mDisplay: " + mDisplay);
    }


}
