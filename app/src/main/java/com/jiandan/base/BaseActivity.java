package com.jiandan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jiandan.R;
import com.jiandan.net.RequestManager;
import com.jiandan.utils.ShowToast;
import com.jiandan.utils.AppManager;

/**
 * Created by hongweiyu on 2015/10/12.
 */
public class BaseActivity extends AppCompatActivity implements Initialable {

    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);//关闭本页面
        RequestManager.cancelAll(this);//将本页面所有的网络请求取消
    }

    //execute the net request in the activity
    protected void executeRequest(Request<?> request){
        RequestManager.addRequest(request,this);
    }

    //请求错误的回调方法
    protected Response.ErrorListener errorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ShowToast.shortShow(error.getMessage());
            }
        };
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    /**
     * activity页面默认的退出效果
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none,R.anim.trans_center_2_right);
    }
}
