package com.aglook.comapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/11/12.
 */
public class SelectPopupWindow extends PopupWindow {

    private final View mMenuView;
    private final TextView tv_moren_select_popup;
    private final TextView tv_delete_select_popup;
    private final TextView tv_concal_select_popup;

    public SelectPopupWindow(Activity activity,View.OnClickListener itemsOnClick) {
        LayoutInflater inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.layout_select_popup, null);
        tv_moren_select_popup = (TextView) mMenuView.findViewById(R.id.tv_moren_select_popup);
        tv_delete_select_popup = (TextView) mMenuView.findViewById(R.id.tv_delete_select_popup);
        tv_concal_select_popup = (TextView) mMenuView.findViewById(R.id.tv_concal_select_popup);
            //取消
        tv_concal_select_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //设置监听
        tv_moren_select_popup.setOnClickListener(itemsOnClick);
        tv_delete_select_popup.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    public void setText(String t1,String t2){
        tv_moren_select_popup.setText(t1);
        tv_delete_select_popup.setText(t2);
    }

    public void setTextColor(int c1,int c2){
        tv_moren_select_popup.setTextColor(c1);
        tv_delete_select_popup.setTextColor(c2);
    }
}
