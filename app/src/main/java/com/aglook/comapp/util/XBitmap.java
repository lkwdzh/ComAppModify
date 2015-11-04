package com.aglook.comapp.util;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by aglook on 2015/10/26.
 */
public class XBitmap {
    public static void displayImage(View imageView,String url,Context context){
        Log.d("图片url",url);
        BitmapUtils bitmapUtils=new BitmapUtils(context);
//        bitmapUtils.configDefaultLoadingImage(context.getResources().getDrawable(
//                R.drawable.tanluicon));// 默认背景图片
//        bitmapUtils.configDefaultLoadFailedImage(context.getResources().getDrawable(
//                R.drawable.tanluicon));// 加载失败图片

        bitmapUtils.display(imageView, url);
    }
}
