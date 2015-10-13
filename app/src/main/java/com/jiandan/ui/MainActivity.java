package com.jiandan.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jiandan.R;
import com.jiandan.base.BaseActivity;
import com.jiandan.base.Initialable;
import com.jiandan.modal.NetWorkEvent;
import com.jiandan.ui.fragment.FreshNewsFragment;
import com.jiandan.ui.fragment.MainMenuFragment;
import com.jiandan.utils.AppManager;
import com.jiandan.utils.NetWorkUtils;
import com.jiandan.utils.ScreenSizeUtils;
import com.jiandan.utils.ShowToast;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import me.drakeet.materialdialog.MaterialDialog;


public class MainActivity extends ActionBarActivity implements Initialable{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MaterialDialog noNetWorkDialog;//提示框
    private BroadcastReceiver netStateReceiver;//网络状态监听

    //计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        //为ActionBar添加点击事件
        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ScreenSizeUtils.getScreenWidth(MainActivity.this)/2,
                LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setBackgroundColor(Color.TRANSPARENT);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setCustomView(linearLayout);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name,
                R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        replaceFragment(R.id.frame_container, new FreshNewsFragment());
        replaceFragment(R.id.drawer_container, new MainMenuFragment());

    }


    @Override
    public void initData() {

        netStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(
                        ConnectivityManager.CONNECTIVITY_ACTION)){
                    if(NetWorkUtils.isNetConnected(MainActivity.this)){
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.AVAILABLE));
                    }else {
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.UNAVAILABLE));
                    }
                }
            }
        };

        //注册网络状态广播接收者
        registerReceiver(netStateReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
        ));


    }

    //replace the fragment dynamic
    private void replaceFragment(int container, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @Subscribe
    public void onEvent(NetWorkEvent event){
        if(event.getType() == NetWorkEvent.UNAVAILABLE){
            if(noNetWorkDialog == null){

                noNetWorkDialog = new MaterialDialog(MainActivity.this);
                noNetWorkDialog.setTitle("无网络连接");
                noNetWorkDialog.setMessage("现在去开启网络");
                noNetWorkDialog.setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noNetWorkDialog.dismiss();
                    }
                });
                noNetWorkDialog.setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intent);
                    }
                });
                noNetWorkDialog.show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis() - exitTime > 2000){
                ShowToast.shortShow("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            }else{
                AppManager.getInstance().finishAllActivityAndExit();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netStateReceiver);
    }

}
