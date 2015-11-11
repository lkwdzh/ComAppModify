package com.aglook.comapp.Application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/11/11.
 */
public class ExitApplication extends Application {
    private List<Activity>activityList=new ArrayList<>();
    private static ExitApplication instance;

    public static ExitApplication getInstance(){
        if (null==instance){
            instance=new ExitApplication();
        }
        return instance;
    }

    //添加Activity到容器
    public void addActivity(Activity activity){
        activityList.add(activity);
    }

//    遍历退出
    public void exit(){
        for (Activity activity:activityList){
            activity.finish();
        }
        System.exit(0);
    }
}
