package com.jiandan.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jiandan.net.RequestManager;
import com.jiandan.ui.MainActivity;
import com.jiandan.utils.ShowToast;

/**
 * Created by hongweiyu on 2015/10/13.
 *
 * Fragment基类
 *
 */
public class BaseFragment extends Fragment {

    protected ActionBar mActionBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActionBar =((AppCompatActivity) activity).getSupportActionBar();

        if(activity instanceof MainActivity){
            mActionBar.getCustomView()
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onActionBarClick();
                        }
                    });
        }

    }

    //重写该方法,处理在MainActivity下的ActionBar点击事件
    private void onActionBarClick() {

    }

    //add the network request
    protected void executeRequest(Request<?> request){
        RequestManager.addRequest(request, this);
    }


    protected Response.ErrorListener errorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ShowToast.shortShow(error.getMessage());
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //cancel all the net work request;
        RequestManager.cancelAll(this);
    }
}
