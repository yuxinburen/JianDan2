package com.jiandan.utils;

import android.app.Activity;

/**
 * Created by hongweiyu on 2015/10/12.
 *
 * 获取屏幕尺寸大小工具类
 *
 */
public class ScreenSizeUtils {

    //return the activity screen width size
    public static int getScreenWidth(Activity activity){
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    //return the activity screen height size
    public static int getScreenHeight(Activity activity){
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }
}
