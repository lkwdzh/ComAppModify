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
import android.widget.TextView;

import com.aglook.comapp.Activity.CardListActivity;
import com.aglook.comapp.Activity.GuaDanAddActivity;
import com.aglook.comapp.Activity.PickInfoActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.AllOrderDataList;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.XBitmap;

import java.util.List;

/**
 * Created by aglook on 2015/11/10.
 */
public class OrderDetailAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity context;
    private List<AllOrderDataList> list;
    private boolean isSuccess;

    public OrderDetailAdapter(Activity context, List<AllOrderDataList> list) {
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

    public void isSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_all_order_lv_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AllOrderDataList dataList = list.get(position);
        XBitmap.displayImage(holder.iv_lv_lv, dataList.getProductLogo(), context);
        holder.tv_name_lv_lv.setText(dataList.getProductName());
        holder.tv_price_lv_lv.setText(dataList.getProductMoney() + "");
        holder.tv_weight_lv_lv.setText(dataList.getWeightUseable() + "吨");
        holder.tv_num_lv_lv.setText(dataList.getProductMoneyYh() + "");
//        holder.tv_num_lv_lv.setText(dataList.getProductNum());
        holder.tv_sell_all_order_lv.setTag(position);
        holder.tv_pick_all_order_lv.setTag(position);
        if (!isSuccess || dataList.getWeightUseable().equals("0")) {
            holder.tv_sell_all_order_lv.setVisibility(View.GONE);
            holder.tv_pick_all_order_lv.setVisibility(View.GONE);
        } else {
            holder.tv_sell_all_order_lv.setVisibility(View.VISIBLE);
            holder.tv_pick_all_order_lv.setVisibility(View.VISIBLE);
        }
        holder.tv_sell_all_order_lv.setOnClickListener(this);
        holder.tv_pick_all_order_lv.setOnClickListener(this);
        return convertView;
    }

    private int index;

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_pick_all_order_lv:
                index = (int) v.getTag();
                //提货
                intent.setClass(context, PickInfoActivity.class);
                intent.putExtra("code", "2002");
                intent.putExtra("isPlate", true);
                intent.putExtra("orderdataId", list.get(index).getOrderdataId());
                intent.putExtra("originalListId", list.get(index).getProductListId());
                context.startActivityForResult(intent, 13);
                break;
            case R.id.tv_sell_all_order_lv:
                index = (int) v.getTag();
                //如果没有绑定银行卡，不能出售
                if (DefineUtil.BANKBAND) {
                    //转售
                    intent.setClass(context, GuaDanAddActivity.class);
                    intent.putExtra("code", "2002");
                    intent.putExtra("codeGua", "2003");
                    intent.putExtra("isPlate", true);
                    intent.putExtra("orderdataId", list.get(index).getOrderdataId());
                    intent.putExtra("originalListId", list.get(index).getProductListId());
                    context.startActivityForResult(intent, 13);
                } else {
                    showDialog();
                }
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
//    @Override
//    public boolean isEnabled(int position) {
//        return false;
//    }

    class ViewHolder {
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;
        TextView tv_sell_all_order_lv;
        TextView tv_pick_all_order_lv;

        ViewHolder(View view) {
            tv_pick_all_order_lv = (TextView) view.findViewById(R.id.tv_pick_all_order_lv);
            tv_sell_all_order_lv = (TextView) view.findViewById(R.id.tv_sell_all_order_lv);
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv = (TextView) view.findViewById(R.id.tv_num_lv_lv);
        }
    }
}
