package com.jiandan.net;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jiandan.base.AppAplication;

/**
 * Created by hongweiyu on 2015/10/12.
 *
 *  网络请求管理类
 */
public class RequestManager {

    //the queue of net request
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(AppAplication.getContext());

    private RequestManager(){}

    //add the request
    public static void addRequest(Request<?> request, Object tag){
        if(tag!=null){
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    //cancel the request
    public static void cancelAll(Object tag){
        mRequestQueue.cancelAll(tag);
    }




}
