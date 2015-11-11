package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/11/10.
 */
public class PickUpAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;

    public PickUpAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 3;
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
        if (convertView==null){
            convertView= LayoutInflater.from(activity).inflate(R.layout.layout_my_cang_dan,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.ll_right.setVisibility(View.VISIBLE);
        holder.tv_tihuo_all_order_lv.setVisibility(View.GONE);
        holder.tv_trans_all_order_lv.setText("取消提货");
        holder.tv_trans_all_order_lv.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_trans_all_order_lv:
                showDailog();
                break;
            case R.id.btn_cancel_delete:
               dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                dialog.dismiss();
                break;
        }
    }

    class ViewHolder{
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_type_lv_lv;
        TextView tv_grade_lv_lv;
        TextView tv_address_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;
        TextView tv_house_num_my_cangdan;
        TextView tv_in_time_my_cangdan;
        TextView tv_trans_all_order_lv;
        TextView tv_tihuo_all_order_lv;
        LinearLayout ll_right;
        ViewHolder(View view) {
            iv_lv_lv=(ImageView)view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv=(TextView)view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv=(TextView)view.findViewById(R.id.tv_price_lv_lv);
            tv_type_lv_lv=(TextView)view.findViewById(R.id.tv_type_lv_lv);
            tv_grade_lv_lv=(TextView)view.findViewById(R.id.tv_grade_lv_lv);
            tv_address_lv_lv=(TextView)view.findViewById(R.id.tv_address_lv_lv);
            tv_weight_lv_lv=(TextView)view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv=(TextView)view.findViewById(R.id.tv_num_lv_lv);
            tv_house_num_my_cangdan=(TextView)view.findViewById(R.id.tv_house_num_my_cangdan);
            tv_in_time_my_cangdan=(TextView)view.findViewById(R.id.tv_in_time_my_cangdan);
            tv_trans_all_order_lv=(TextView)view.findViewById(R.id.tv_trans_all_order_lv);
            tv_tihuo_all_order_lv=(TextView)view.findViewById(R.id.tv_tihuo_all_order_lv);
            ll_right=(LinearLayout)view.findViewById(R.id.ll_right);
        }
    }

    private Dialog dialog;
    private TextView tv_delete_order;
    public void showDailog(){
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.layout_order, null);
        btn_cancel_delete= (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
        tv_delete_order.setText("确认删除此提货单?");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }
    private Button btn_cancel_delete;
    private Button btn_confirm_delete;

}
