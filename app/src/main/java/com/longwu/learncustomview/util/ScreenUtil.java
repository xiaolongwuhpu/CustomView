package com.longwu.learncustomview.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 获取手机屏幕的宽高
 * 
 * @author Luke
 *
 */
public final class ScreenUtil {
	private ScreenUtil() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 获取屏幕中控件底部位置的高度--即控件底部的Y点
	 * 
	 * @return
	 */
	public static int getScreenViewBottomHeight(View view) {
		return view.getBottom();
	}

	/**
	 * 获取屏幕中控件顶部位置的高度--即控件底部的Y点
	 * 
	 * @return
	 */
	public static int getScreenViewTopHeight(View view) {
		return view.getTop();
	}

	/**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public final static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		return outMetrics.widthPixels;
	}
	/**
	 * 获得屏幕高度
	 *
	 * @param context
	 * @return
	 */
	public final static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		return outMetrics.heightPixels;
	}

	/**
	 * 获取屏幕宽高比,用于获取最佳的相机预览屏幕
	 * @param context
	 * @return
	 */
	public final static double getScreenRatio(Context context) {
		return (double) getScreenWidth(context)/(double)getScreenHeight(context);
	}
	/**
	 * //透明状态栏
	 * getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	 * //透明导航栏 getWindow().addFlags(WindowManager.LayoutParams.
	 * FLAG_TRANSLUCENT_NAVIGATION);
	 * 
	 * 获取状态栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 获取导航栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getDaoHangHeight(Context context) {
		int result = 0;
		int resourceId = 0;
		int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
		if (rid != 0) {
			resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
			return context.getResources().getDimensionPixelSize(resourceId);
		} else
			return 0;
	}



	/**
	 * 调节屏幕亮度
	 * 
	 * @param paramInt
	 *            0-255
	 * @return
	 */
	public final static void setScreenBrightness(Activity activity, int paramInt) {
		Window localWindow = activity.getWindow();
		WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
		float f = paramInt / 255.0F;
		localLayoutParams.screenBrightness = f;
		localWindow.setAttributes(localLayoutParams);
	}
}