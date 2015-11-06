package com.aglook.comapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.view.MyGridView;

/**
 * Created by aglook on 2015/11/6.
 */
public class AllOrderAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private TextView tv_delete_order;

    public AllOrderAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.layout_all_order_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_click_all_order_lv.setText("再次购买");
        holder.iv_delete_all_order_lv.setOnClickListener(this);
        holder.gv_all_order_lv.setAdapter(holder.adapter);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_delete_all_order_lv:
                showWindow(v);
                break;
            case R.id.btn_cancel_delete:
                closeWindow();
                break;
            case R.id.btn_confirm_delete:
                closeWindow();
                break;
        }
    }

    class ViewHolder {
        TextView tv_name_all_order_lv;
        ImageView iv_delete_all_order_lv;
        MyGridView gv_all_order_lv;
        TextView tv_money_all_order_lv;
        TextView tv_click_all_order_lv;
        AllOrderGVAdapter adapter;

        ViewHolder(View view) {
            tv_name_all_order_lv = (TextView) view.findViewById(R.id.tv_name_all_order_lv);
            iv_delete_all_order_lv = (ImageView) view.findViewById(R.id.iv_delete_all_order_lv);
            gv_all_order_lv = (MyGridView) view.findViewById(R.id.gv_all_order_lv);
            tv_money_all_order_lv = (TextView) view.findViewById(R.id.tv_money_all_order_lv);
            tv_click_all_order_lv = (TextView) view.findViewById(R.id.tv_click_all_order_lv);
            adapter=new AllOrderGVAdapter(activity);
        }
    }



    //    popupwindow
    private PopupWindow popupWindow;
    private View popupView;
    private Button btn_cancel_delete;
    private Button btn_confirm_delete;
    public void showWindow(View view) {
        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            popupView = layoutInflater.inflate(R.layout.layout_order, null);
            btn_cancel_delete= (Button) popupView.findViewById(R.id.btn_cancel_delete);
            btn_confirm_delete = (Button) popupView.findViewById(R.id.btn_confirm_delete);
            tv_delete_order = (TextView) popupView.findViewById(R.id.tv_delete_order);
            tv_delete_order.setText("确认删除此订单?");
            popupWindow = new PopupWindow(popupView, 500, 300);
        }
        backgroundAlpha(0.5f);
//        使其聚焦
//        popupWindow.setFocusable(true);
        //允许在外点击消失
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.update();
//        点击back 返回
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }

    public void closeWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        backgroundAlpha(1f);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

}
