package com.jiandan.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hongweiyu on 2015/10/13.
 *
 * 网络连接状态工具类
 *
 */
public class NetWorkUtils {


    /**
     * 判断当前网络是否已经连接
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context){
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            result = true;
        }else {
            result = false;
        }
        return result;
    }


    /**
     * 判断当前网络了连接状态是否为WIFI
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context){
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfo.isConnected()){
            result = true;
        }else {
            result = false;
        }
        return result;
    }
}
