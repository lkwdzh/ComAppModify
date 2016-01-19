package com.aglook.comapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LKW on 2015/10/26.
 */
public class AppUtils {
    //EditText获取字符串
    public static String toStringTrim_ET(EditText editText){
        return editText.getText().toString().trim();
    }

    //TextView获取字符串
    public static String toStringTrim_TV(TextView textView){
        return textView.getText().toString().trim();
    }

    //Toast封装
    public static void toastTextNew(Context context,String toastString){
        Toast toast = Toast.makeText(context, toastString, Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.CENTER,0,0);
//        View view= LayoutInflater.from(context).inflate(R.layout.toast_view,null);
//        TextView viewById = (TextView) view.findViewById(R.id.tv_toast);
//        viewById.setText(toastString);
//        toast.setView(view);
        toast.show();
    }

    //Toast封装
    public static void toastText(Context context,String toastString){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage(toastString);
        builder.setTitle("提示");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}

