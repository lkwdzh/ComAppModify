package com.aglook.comapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aglook.comapp.Activity.OrderDetailActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.view.MyListView;

/**
 * Created by aglook on 2015/11/6.
 */
public class ToPayAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private TextView tv_delete_order;

    public ToPayAdapter(Activity activity) {
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
        holder.lv_all_order_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, OrderDetailActivity.class);
                activity.startActivity(intent);
            }
        });
        holder.tv_click_all_order_lv.setText("确认付款");
        holder.tv_click_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_delete_all_order_lv.setVisibility(View.GONE);
        holder.tv_click_all_order_lv.setOnClickListener(this);
        holder.lv_all_order_lv.setAdapter(holder.adapter);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_click_all_order_lv:
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
        TextView tv_order_num_all_order_lv;
        TextView tv_success_all_order_lv;
        MyListView lv_all_order_lv;
        TextView tv_order_total_all_order_lv;
        TextView tv_money_all_order_lv;
        TextView tv_cost_all_order_lv;
        TextView tv_click_all_order_lv;
        AllOrderLVAdapter adapter;
        TextView tv_delete_all_order_lv;

        ViewHolder(View view) {
            tv_order_num_all_order_lv = (TextView) view.findViewById(R.id.tv_order_num_all_order_lv);
            tv_success_all_order_lv = (TextView) view.findViewById(R.id.tv_success_all_order_lv);
            lv_all_order_lv = (MyListView) view.findViewById(R.id.lv_all_order_lv);
            tv_order_total_all_order_lv = (TextView) view.findViewById(R.id.tv_order_total_all_order_lv);
            tv_money_all_order_lv = (TextView) view.findViewById(R.id.tv_money_all_order_lv);
            tv_cost_all_order_lv = (TextView) view.findViewById(R.id.tv_cost_all_order_lv);
            tv_click_all_order_lv = (TextView) view.findViewById(R.id.tv_click_all_order_lv);
            tv_delete_all_order_lv=(TextView)view.findViewById(R.id.tv_delete_all_order_lv);
            adapter=new AllOrderLVAdapter(activity);
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
