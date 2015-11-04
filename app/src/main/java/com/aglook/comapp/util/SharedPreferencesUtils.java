package com.aglook.comapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aglook on 2015/10/28.
 */
public class SharedPreferencesUtils {

    public static String SP_NAME="aglooksp";
    private static SharedPreferences sp;

    public static void saveBoolean(Context context,String key,boolean value){
        if (sp==null){
            sp=context.getSharedPreferences(SP_NAME,0);
        }
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context context,String key,boolean defValue){
        if (sp==null){
            sp=context.getSharedPreferences(SP_NAME,0);
        }
        return sp.getBoolean(key,defValue);
    }

    public static void saveString(Context context,String key,String value){
            if (sp==null){
                sp=context.getSharedPreferences(SP_NAME,0);
            }
        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context context,String key,String defValue){
        if (sp==null){
            sp=context.getSharedPreferences(SP_NAME,0);
        }
        return sp.getString(key,defValue);
    }

}
