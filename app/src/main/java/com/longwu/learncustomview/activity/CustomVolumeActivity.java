package com.longwu.learncustomview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;

import com.longwu.learncustomview.BaseActivity;
import com.longwu.learncustomview.R;
import com.longwu.learncustomview.view.DrawCircleView;

import butterknife.BindView;

/**
 * Created by wujing on 2017/4/26 0026.
 */

public class CustomVolumeActivity extends BaseActivity {
    CustomVolumeActivity.MyThread MyThread;
    MyHandler myHandler;

//    @BindView(R.id.cv1)
//    DrawCircleView cv1;
//    @BindView(R.id.cv2)
//    DrawCircleView cv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_volume);
        myHandler = new MyHandler();
        MyThread = new MyThread();
        new Thread(MyThread).start();
    }

    int progresscount = 0;

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
//                    cv1.setmMax((int) msg.obj);
                    break;
            }
        }
    }

    class MyThread implements Runnable {
        public MyThread() {
        }
        @Override
        public void run() {
            do {
                Message msg = myHandler.obtainMessage();
                msg.what = 1;
                msg.obj = progresscount;
                myHandler.sendMessage(msg);
                progresscount++;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (progresscount <= 360);

        }
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
        mCommonToolbar.setCenterTitle("圆环");
    }

    @Override
    public void initStatusBar() {

    }

    @Override
    public void initView() {

    }
}
