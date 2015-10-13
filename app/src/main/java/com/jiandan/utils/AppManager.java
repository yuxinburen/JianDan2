package com.jiandan.utils;

import android.app.Activity;
import android.view.animation.AccelerateInterpolator;

import java.util.Stack;

/**
 * Created by hongweiyu on 2015/10/12.
 *
 * 应用程序Activity管理类;用于Activity管理和应用程序退出
 *
 * 单例模式
 *
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager(){}

    public static AppManager getInstance(){
        if(instance == null){
            instance = new AppManager();
        }
            return instance;
    }

    //add the activity
    public void addActivity(Activity activity){
        if(activityStack == null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    //get the current :also the last element in stack of Activity
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //finish the current activity: remove the last element of activityStack
    public void finishCurrentActivity(){
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    //finish the arm activity :remove the element by name
    public void finishActivity(Activity activity) {
        if(activity !=null){
            activityStack.remove(activity);
            activity.finish();
            activity = null; //release the system resources
        }
    }

    //finish the arm activity: remove the element by className
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack){
            if(activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }

    //finish the all activityes :remove the all elements of activityStack
    public void finishAllActivityAndExit(){
        if(null != activityStack){
            for (int i = 0, size = activityStack.size(); i<size;i++){
                if(null != activityStack.get(i)){
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }
}
