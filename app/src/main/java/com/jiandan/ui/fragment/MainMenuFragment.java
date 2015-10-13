package com.jiandan.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiandan.base.BaseFragment;
import com.jiandan.ui.MainActivity;

/**
 * Created by hongweiyu on 2015/10/13.
 *
 * 左侧菜单的Fragment
 *
 */
public class MainMenuFragment extends BaseFragment{

    private MainActivity mainActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(mainActivity instanceof MainActivity){
                mainActivity = (MainActivity) activity;
        }else {
            throw new IllegalArgumentException("The Activity must be MainActivity !");
        }
    }
}
