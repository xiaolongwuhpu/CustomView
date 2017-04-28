package com.longwu.learncustomview.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by wujing on 2017/4/24 0024.
 */

public class NetWorkUtil {
    /**
     * 无网络链接
     */
    public static final int NET_NO_CONNECTION = 0;
    /**
     * wifi
     */
    public static final int NET_TYPE_WIFI = 1;

    public static final int NET_TYPE_2G = 2;

    public static final int NET_TYPE_3G = 3;

    public static final int NET_TYPE_4G = 4;

    /**
     * 未知的网络类型
     */
    public static final int NET_TYPE_UNKNOWN = 5;

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static int getConnectionType(Context context) {

        int netType = NET_NO_CONNECTION;

        NetworkInfo networkInfo = ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (networkInfo == null) {

            netType = NET_NO_CONNECTION;
        } else {

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NET_TYPE_WIFI;
            } else {

                int networkType = networkInfo.getSubtype();

                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        netType = NET_TYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9:replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11:replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13:replace by 15
                        netType = NET_TYPE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11:replace by 13
                        netType = NET_TYPE_4G;
                        break;
                    default:

                        String subType = networkInfo.getSubtypeName();

                        if (subType.equalsIgnoreCase("TD-SCDMA") ||
                                subType.equalsIgnoreCase("WCDMA") ||
                                subType.equalsIgnoreCase("CDMA2000")) {
                            netType = NET_TYPE_3G;
                        } else {
                            netType = NET_TYPE_UNKNOWN;
                        }

                        break;
                }
            }
        }
        return netType;
    }
}
