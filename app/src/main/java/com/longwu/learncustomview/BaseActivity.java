package com.longwu.learncustomview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.longwu.learncustomview.view.MyToolBar;
import com.longwu.learncustomview.widget.statusbar.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wujing on 2017/4/24 0024.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static String TAG;
    public MyToolBar mCommonToolbar;
    protected Activity mContext;
    private Unbinder unbinder;
    //    protected SystemBarTintManager tintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MainApplication.getInstance().pushActivity(this);
        mContext = this;
        TAG = getClass().getName().toString();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        this.init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        this.init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);

        this.init();
    }

    private void init() {
        unbinder = ButterKnife.bind(this);
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black), 100);//浸透状态栏
        //        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        // 浸透状态栏
        if (mCommonToolbar != null) {
//            LogUtil.d("toolbar", "显示了toolbar");
            setSupportActionBar(mCommonToolbar);
            mCommonToolbar.setCenterTitleColor(R.color.title_text_color);
            mCommonToolbar.getCenterTextView().setTextSize(18);
            setTitle("");
            initToolBar();
        }
//        ReactNativeReqBean re = MainApplication.getInstance().getReactNativeReqBean();
//        if (re ==null) {
//            InitData();
//        }
        initStatusBar();
        initView();
    }


    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
//        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
//        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
//        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


    /**
     * 设置toolbar
     */
    public abstract void initToolBar();

    /**
     * 设置StatusBar
     */
    public abstract void initStatusBar();

    /**
     * 处理数据
     */
    public abstract void initView();

    /**
     * 显示隐藏view
     *
     * @param views
     */
    protected void setViewsVisible(int visible, final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(visible);
                }
            }
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
//    public void onColseAllActivityEvent(CloseAllRNActivityEvent event) {
//        finish();
//    }

//    /**
//     * 急速申请首页返回结束页面发通知
//     */
//    public void closeAllActivity() {
//        try {
//            ReactNativeResBean reactNativeResBean = new ReactNativeResBean(100, new
//                    ReactNativeResBean.ReactNativeResData());
//            Intent intent = new Intent();  //Intent就是我们要发送的内容
//            intent.putExtra("result", JSON.toJSONString(reactNativeResBean));
//            intent.setAction(AppUtil.getAppPkgName() + AppConfig.BROADCASE_ADDRESS);
//            //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
//            sendBroadcast(intent);   //发送广播
//        } catch (Exception e) {
//            LogUtil.e(TAG, "提交信息失败，请重试");
//        }
//    }
}
