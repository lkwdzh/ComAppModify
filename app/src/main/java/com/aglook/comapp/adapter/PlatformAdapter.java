package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.CardListActivity;
import com.aglook.comapp.Activity.GuaDanAddActivity;
import com.aglook.comapp.Activity.PickInfoActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.PlatformCangDanList;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.view.Timestamp;

import java.util.List;

/**
 * Created by aglook on 2015/11/11.
 */
public class PlatformAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity context;
    private List<PlatformCangDanList> list;
    private int index;

    public PlatformAdapter(Activity context, List<PlatformCangDanList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_my_cang_dan, null);
            holder = new ViewHolder(convertView);
            holder.tv_trans_all_order_lv.setTag(position);
            holder.tv_tihuo_all_order_lv.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_in_time.setText("交易时间");
        holder.ll_3.setVisibility(View.INVISIBLE);
        holder.tv_trans_all_order_lv.setOnClickListener(this);
        holder.tv_tihuo_all_order_lv.setOnClickListener(this);
        holder.tv_cangdan.setText("平台仓单编号");
        PlatformCangDanList danList = list.get(position);
        holder.tv_house_num_my_cangdan.setText(danList.getOrderId());

        if (danList.getOrderState().equals("success")){
            holder.tv_success_all_order_lv.setText("交易成功");
        }else  if (danList.getOrderState().equals("notpay")){
            holder.tv_success_all_order_lv.setText("交易中");
        }else  if (danList.getOrderState().equals("close")){
            holder.tv_success_all_order_lv.setText("交易关闭");
        }

        XBitmap.displayImage(holder.iv_lv_lv, danList.getProductLogo(), context);
        holder.tv_name_lv_lv.setText(danList.getProductName());
        holder.tv_price_lv_lv.setText(danList.getProductMoney());
        holder.tv_weight_lv_lv.setText(danList.getWeightUseable() + "吨");
        if (danList.getWeightUseable()!=null&&!"".equals(danList.getWeightUseable())){

        if (danList.getWeightUseable().equals("0")){
            //仓单交易与提货不可见
            holder.tv_trans_all_order_lv.setVisibility(View.GONE);
            holder.tv_tihuo_all_order_lv.setVisibility(View.GONE);
        }else {
            holder.tv_trans_all_order_lv.setVisibility(View.VISIBLE);
            holder.tv_tihuo_all_order_lv.setVisibility(View.VISIBLE);
        }
        }
        holder.tv_trans_all_order_lv.setTag(position);
        if (danList.getOrderPtime()!=null&&!"".equals(danList.getOrderPtime())){
            holder.tv_in_time_my_cangdan.setText(Timestamp.getDateTo(danList.getOrderPtime()));
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_trans_all_order_lv:
                //如果没有绑定银行卡，不能出售
                if (DefineUtil.BANKBAND) {
                    intent.setClass(context, GuaDanAddActivity.class);
                    index = (int) v.getTag();
                    intent.putExtra("orderdataId", list.get(index).getOrderdataId());
                    intent.putExtra("code", "2002");
                    intent.putExtra("codeGua", "2003");
                    intent.putExtra("isPlate", true);
                    context.startActivityForResult(intent, 2);
                }else {
                    showDialog();
                }
                break;
            case R.id.tv_tihuo_all_order_lv:
                index = (int) v.getTag();
                intent.setClass(context, PickInfoActivity.class);
                intent.putExtra("orderdataId", list.get(index).getOrderdataId());
                intent.putExtra("code", "2002");
                intent.putExtra("isPlate", true);
                context.startActivityForResult(intent,2);
                break;
            case R.id.btn_cancel_pay_popup:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_pay_popup:
                intent.setClass(context, CardListActivity.class);
                context.startActivity(intent);
                dialog.dismiss();
                break;
        }
    }
    private Dialog dialog;
    private Button btn_cancel_pay_popup;
    private Button btn_confirm_pay_popup;
    private TextView tv_message;

    public void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inView = inflater.inflate(R.layout.layout_pay_dialog, null);
        btn_cancel_pay_popup = (Button) inView.findViewById(R.id.btn_cancel_pay_popup);
        btn_confirm_pay_popup = (Button) inView.findViewById(R.id.btn_confirm_pay_popup);
        tv_message = (TextView) inView.findViewById(R.id.tv_message);
        tv_message.setText("尚未绑定银行卡，不能支付，去绑定？");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.create();
        builder.setView(inView);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_pay_popup.setOnClickListener(this);
        btn_confirm_pay_popup.setOnClickListener(this);
    }
    class ViewHolder {
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;
        TextView tv_house_num_my_cangdan;
        TextView tv_in_time_my_cangdan;
        TextView tv_trans_all_order_lv;
        TextView tv_tihuo_all_order_lv;
        TextView tv_in_time;
        TextView tv_cangdan;
        LinearLayout ll_1;
        LinearLayout ll_3;
        TextView tv_success_all_order_lv;

        ViewHolder(View view) {
            tv_success_all_order_lv=(TextView)view.findViewById(R.id.tv_success_all_order_lv);
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv = (TextView) view.findViewById(R.id.tv_num_lv_lv);
            tv_house_num_my_cangdan = (TextView) view.findViewById(R.id.tv_house_num_my_cangdan);
            tv_in_time_my_cangdan = (TextView) view.findViewById(R.id.tv_in_time_my_cangdan);
            tv_trans_all_order_lv = (TextView) view.findViewById(R.id.tv_trans_all_order_lv);
            tv_tihuo_all_order_lv = (TextView) view.findViewById(R.id.tv_tihuo_all_order_lv);
            tv_in_time = (TextView) view.findViewById(R.id.tv_in_time);
            tv_cangdan = (TextView) view.findViewById(R.id.tv_cangdan);
            ll_1 = (LinearLayout) view.findViewById(R.id.ll_1);
            ll_3 = (LinearLayout) view.findViewById(R.id.ll_3);
        }
    }
}
