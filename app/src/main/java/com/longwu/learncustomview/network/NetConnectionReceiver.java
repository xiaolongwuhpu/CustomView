package com.longwu.learncustomview.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.longwu.learncustomview.Myapplication;
import com.longwu.learncustomview.util.NetWorkUtil;

/**
 * Created by wujing on 2017/4/24 0024.
 */

public class NetConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int connectionType = NetWorkUtil.getConnectionType(context);
            /**
             * 更改网络状态
             */
            if (Myapplication.getInstance() != null) {
                Myapplication.getInstance().notifyNetObserver(connectionType);
            }
        }
    }
}
