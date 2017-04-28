package com.longwu.learncustomview;

import android.app.Application;

import com.longwu.learncustomview.network.NetConnectionObserver;
import com.longwu.learncustomview.network.NetConnectionSubject;
import com.longwu.learncustomview.util.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujing on 2017/4/24 0024.
 */

public class Myapplication extends Application implements NetConnectionSubject {

    protected static Myapplication instance;

    private int currentNetType = -1;

    private List<NetConnectionObserver> observers = new ArrayList<>();
    public static Myapplication getInstance() {
        if(instance == null){
            instance = new Myapplication();
        }
        return instance;
    }

    /**
     * current net connection type
     *
     * @return
     */
    public int getCurrentNetType() {
        return currentNetType;
    }

    /**
     * current net connection status
     *
     * @return
     */
    public boolean isNetConnection() {
        return currentNetType == NetWorkUtil.NET_NO_CONNECTION ? false : true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        IntentFilter intentFilter = new IntentFilter(conn);
////                intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS); //监听HOME键的监听事件,
//        registerReceiver(broadcastReceiver, intentFilter);

        currentNetType = NetWorkUtil.getConnectionType(this);

    }

    @Override
    public void addNetObserver(NetConnectionObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeNetObserver(NetConnectionObserver observer) {
        if (observers != null && observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyNetObserver(int type) {

        /**
         * 避免多次发送相同的网络状态
         */
        if (currentNetType == type) {
            return;
        } else {
            currentNetType = type;
            if (observers != null && observers.size() > 0) {
                for (NetConnectionObserver observer : observers) {
                    observer.updateNetStatus(type);
                }
            }
        }
    }


}
