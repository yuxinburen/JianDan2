package com.jiandan.utils;

import android.app.Application;
import android.widget.Toast;

import com.jiandan.base.AppAplication;

/**
 * Created by hongweiyu on 2015/10/12.
 *
 * 显示吐司的工具类
 * */
public class ShowToast {

    /**
     * 显示短时间的吐司
     * @param sequence
     */
    public static void shortShow(CharSequence sequence){
        Toast.makeText(AppAplication.getContext(), sequence,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长时间的吐司
     * @param sequence
     */
    public static void longShow(CharSequence sequence){
        Toast.makeText(AppAplication.getContext(), sequence,Toast.LENGTH_LONG).show();
    }

}
