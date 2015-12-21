package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.GuaDanStateActivity;
import com.aglook.comapp.Activity.ModifyGuaDanActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.GuaDanList;
import com.aglook.comapp.url.AllGuaDanUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.Timestamp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.List;

/**
 * Created by aglook on 2015/11/6.
 */
public class AllGuaDanAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private List<GuaDanList> list;
    private int index;
    private String code = "4004";
    private String productId;

    public AllGuaDanAdapter(Activity activity, List<GuaDanList> list) {
        this.activity = activity;
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.layout_my_cang_dan, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_trans_all_order_lv.setText("修改");
        holder.tv_tihuo_all_order_lv.setText("取消");
        holder.tv_state_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_tihuo_all_order_lv.setOnClickListener(this);
        holder.tv_trans_all_order_lv.setOnClickListener(this);

        GuaDanList guaDanList = list.get(position);
        holder.tv_cangdan.setText("挂单编号");
        holder.tv_house_num_my_cangdan.setText(guaDanList.getProductSellid());
        holder.tv_in_time.setText("挂单时间");
        holder.tv_in_time_my_cangdan.setText(Timestamp.getDateTo(guaDanList.getProductAtime()));
        XBitmap.displayImage(holder.iv_lv_lv, guaDanList.getProductLogo(), activity);
        holder.tv_name_lv_lv.setText(guaDanList.getProductName());
        holder.tv_price_lv_lv.setText(guaDanList.getProductMoney());
        holder.tv_weight_lv_lv.setText(guaDanList.getProductNum() + "吨");
        if (guaDanList.getProductStatus() != null && !"".equals(guaDanList.getProductStatus())) {
            if (guaDanList.getProductStatus().equals("close")) {
                holder.tv_success_all_order_lv.setText("已关闭");
                holder.tv_trans_all_order_lv.setVisibility(View.GONE);
                holder.tv_tihuo_all_order_lv.setVisibility(View.GONE);
                holder.tv_state_all_order_lv.setVisibility(View.VISIBLE);
            } else if (guaDanList.getProductStatus().equals("wait")) {
                holder.tv_trans_all_order_lv.setVisibility(View.VISIBLE);
                holder.tv_tihuo_all_order_lv.setVisibility(View.VISIBLE);
                holder.tv_state_all_order_lv.setVisibility(View.VISIBLE);
                if (guaDanList.getProductNum() != null && !"".equals(guaDanList.getProductNum())) {
                    if (guaDanList.getProductNum().equals("0")) {
                        holder.tv_success_all_order_lv.setText("已售完");
                        holder.tv_trans_all_order_lv.setVisibility(View.GONE);
                        holder.tv_tihuo_all_order_lv.setVisibility(View.GONE);
                    } else {
                        holder.tv_success_all_order_lv.setText("交易中");
                        holder.tv_trans_all_order_lv.setVisibility(View.VISIBLE);
                        holder.tv_tihuo_all_order_lv.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
//        holder.tv_order_num_all_order_lv.setText(order.getOrderId());

        holder.tv_state_all_order_lv.setTag(position);
        holder.tv_state_all_order_lv.setOnClickListener(this);
        holder.tv_tihuo_all_order_lv.setTag(position);
        holder.tv_trans_all_order_lv.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_tihuo_all_order_lv:
                index = (int) v.getTag();
                productId = list.get(index).getProductId();
                showDailog();
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                dialog.dismiss();
                deleteGua();
                break;
            case R.id.tv_trans_all_order_lv:
                intent.setClass(activity, ModifyGuaDanActivity.class);
                index = (int) v.getTag();
                intent.putExtra("productId", list.get(index).getProductId());
                activity.startActivityForResult(intent,12);
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
        TextView tv_weight_lv_lv;
        TextView tv_success_all_order_lv;
        TextView tv_cangdan;
        TextView tv_house_num_my_cangdan;
        TextView tv_in_time_my_cangdan;
        TextView tv_in_time;
        TextView tv_trans_all_order_lv;
        TextView tv_tihuo_all_order_lv;
        TextView tv_state_all_order_lv;
        LinearLayout ll_1;

        ViewHolder(View view) {
            ll_1 = (LinearLayout) view.findViewById(R.id.ll_1);
            tv_state_all_order_lv = (TextView) view.findViewById(R.id.tv_state_all_order_lv);
            tv_in_time_my_cangdan = (TextView) view.findViewById(R.id.tv_in_time_my_cangdan);
            tv_in_time = (TextView) view.findViewById(R.id.tv_in_time);
            tv_house_num_my_cangdan = (TextView) view.findViewById(R.id.tv_house_num_my_cangdan);
            tv_cangdan = (TextView) view.findViewById(R.id.tv_cangdan);
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_success_all_order_lv = (TextView) view.findViewById(R.id.tv_success_all_order_lv);
            tv_trans_all_order_lv = (TextView) view.findViewById(R.id.tv_trans_all_order_lv);
            tv_tihuo_all_order_lv = (TextView) view.findViewById(R.id.tv_tihuo_all_order_lv);
        }
    }

    private Dialog dialog;
    private TextView tv_delete_order;

    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_order, null);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
        tv_delete_order.setText("确认删除此挂单?");
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


    //删除挂单
    public void deleteGua() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_guaDan_delete", productId + "_____" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    list.get(index).setProductStatus("close");
                } else {
                    AppUtils.toastText(activity, message);
                }
                notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.CANG_DAN, AllGuaDanUrl.postDeleteUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, productId), activity);
    }
}
