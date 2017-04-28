package com.longwu.learncustomview.network;

/**
 * Created by wujing on 2017/4/24 0024.
 */

public interface NetConnectionObserver {

    /**
     * 通知观察者更改状态
     *
     * @param type
     */
    public void updateNetStatus(int type);
}
