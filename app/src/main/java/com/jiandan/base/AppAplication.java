package com.jiandan.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by hongweiyu on 2015/10/12.
 */
public class AppAplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }

    //return the context
    public static Context getContext(){
        return mContext;
    }
}
