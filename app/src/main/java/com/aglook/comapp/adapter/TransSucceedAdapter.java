package com.aglook.comapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Activity.GuaDanAddActivity;
import com.aglook.comapp.Activity.GuaDanStateActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.GuaDanStataLiL;
import com.aglook.comapp.view.Timestamp;

import java.util.List;

/**
 * Created by aglook on 2015/11/6.
 */
public class TransSucceedAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private List<GuaDanStataLiL> list;
    private int index;
    private String productId;


    public TransSucceedAdapter(Activity activity, List<GuaDanStataLiL> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.layout_my_cang_dan, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_trans_all_order_lv.setVisibility(View.GONE);
        holder.tv_tihuo_all_order_lv.setVisibility(View.GONE);
        holder.tv_state_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_tihuo_all_order_lv.setOnClickListener(this);
        holder.tv_trans_all_order_lv.setOnClickListener(this);

        GuaDanStataLiL stataLiL = list.get(position);
        holder.tv_cangdan.setText("挂单编号");
        holder.tv_house_num_my_cangdan.setText(stataLiL.getOrderId());
        holder.tv_in_time.setText("挂单时间");
        if (stataLiL.getOrderAtime() != null && !"".equals(stataLiL.getOrderAtime())) {
            holder.tv_in_time_my_cangdan.setText(Timestamp.getDateTo(stataLiL.getOrderAtime()));
        }
//        XBitmap.displayImage(holder.iv_lv_lv, guaDanList.getProductLogo(), activity);
        holder.tv_name_lv_lv.setText(stataLiL.getProductName());
        holder.tv_price_lv_lv.setText(stataLiL.getProductMoney());
        holder.tv_num_lv_lv.setText(stataLiL.getProductNum());
        if (stataLiL.getOrderState().equals("close")) {
            holder.tv_success_all_order_lv.setText("已关闭");
        } else if (stataLiL.getOrderState().equals("success")) {
            holder.tv_success_all_order_lv.setText("支付成功");

        } else if (stataLiL.getOrderState().equals("notpay")) {
            holder.tv_success_all_order_lv.setText("待支付");
        }
//        holder.tv_order_num_all_order_lv.setText(order.getOrderId());

        holder.tv_state_all_order_lv.setTag(position);
        holder.tv_state_all_order_lv.setOnClickListener(this);
        holder.tv_tihuo_all_order_lv.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_tihuo_all_order_lv:
                index = (int) v.getTag();
                productId = list.get(index).getProductId();
                //TODO 查看交易信息
                break;
            case R.id.tv_trans_all_order_lv:
                intent.setClass(activity, GuaDanAddActivity.class);
                activity.startActivity(intent);
                break;
            case R.id.tv_state_all_order_lv:
                index = (int) v.getTag();
                intent.setClass(activity, GuaDanStateActivity.class);
                intent.putExtra("productId", list.get(index).getProductId());
                activity.startActivity(intent);
                break;
        }
    }

    class ViewHolder {
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_type_lv_lv;
        TextView tv_grade_lv_lv;
        TextView tv_address_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;
        TextView tv_order_num_all_order_lv;
        TextView tv_success_all_order_lv;
        TextView tv_order_total_all_order_lv;
        TextView tv_money_all_order_lv;
        TextView tv_cost_all_order_lv;
        TextView tv_cangdan;
        TextView tv_house_num_my_cangdan;
        TextView tv_in_time_my_cangdan;
        TextView tv_in_time;
        TextView tv_trans_all_order_lv;
        TextView tv_tihuo_all_order_lv;
        TextView tv_state_all_order_lv;

        ViewHolder(View view) {
            tv_state_all_order_lv = (TextView) view.findViewById(R.id.tv_state_all_order_lv);
            tv_in_time_my_cangdan = (TextView) view.findViewById(R.id.tv_in_time_my_cangdan);
            tv_in_time = (TextView) view.findViewById(R.id.tv_in_time);
            tv_house_num_my_cangdan = (TextView) view.findViewById(R.id.tv_house_num_my_cangdan);
            tv_cangdan = (TextView) view.findViewById(R.id.tv_cangdan);
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_type_lv_lv = (TextView) view.findViewById(R.id.tv_type_lv_lv);
            tv_grade_lv_lv = (TextView) view.findViewById(R.id.tv_grade_lv_lv);
            tv_address_lv_lv = (TextView) view.findViewById(R.id.tv_address_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv = (TextView) view.findViewById(R.id.tv_num_lv_lv);
            tv_order_num_all_order_lv = (TextView) view.findViewById(R.id.tv_order_num_all_order_lv);
            tv_success_all_order_lv = (TextView) view.findViewById(R.id.tv_success_all_order_lv);
            tv_order_total_all_order_lv = (TextView) view.findViewById(R.id.tv_order_total_all_order_lv);
            tv_money_all_order_lv = (TextView) view.findViewById(R.id.tv_money_all_order_lv);
            tv_cost_all_order_lv = (TextView) view.findViewById(R.id.tv_cost_all_order_lv);
            tv_trans_all_order_lv = (TextView) view.findViewById(R.id.tv_trans_all_order_lv);
            tv_tihuo_all_order_lv = (TextView) view.findViewById(R.id.tv_tihuo_all_order_lv);
        }
    }

//    private Dialog dialog;
//    private TextView tv_delete_order;
//
//    public void showDailog() {
//        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.layout_order, null);
//        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
//        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
//        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
//        tv_delete_order.setText("确认删除此挂单?");
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.create();
//        builder.setView(view);
////        builder.setCancelable(false);
//        dialog = builder.show();
//        btn_cancel_delete.setOnClickListener(this);
//        btn_confirm_delete.setOnClickListener(this);
//    }
//
//    private Button btn_cancel_delete;
//    private Button btn_confirm_delete;



}
