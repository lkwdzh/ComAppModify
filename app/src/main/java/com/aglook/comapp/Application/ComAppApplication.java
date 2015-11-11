package com.aglook.comapp.Application;

import android.app.Application;

/**
 * Created by aglook on 2015/11/11.
 */
public class ComAppApplication extends Application {
    private static ComAppApplication instance;

    public static ComAppApplication getInstance(){
        if (null==instance){
            instance=new ComAppApplication();
        }
        return instance;
    }
}
