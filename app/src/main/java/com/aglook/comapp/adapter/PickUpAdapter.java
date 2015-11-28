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

import com.aglook.comapp.Activity.ModifyPickUpActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.PickUpList;
import com.aglook.comapp.url.PickUpUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.List;

/**
 * Created by aglook on 2015/11/10.
 */
public class PickUpAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private List<PickUpList> list;
    private CustomProgress customProgress;
    private int index;

    public PickUpAdapter(Activity activity, List<PickUpList> list) {
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

        holder.ll_right.setVisibility(View.VISIBLE);
        holder.tv_tihuo_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_trans_all_order_lv.setText("修改");
        holder.tv_tihuo_all_order_lv.setText("取消");
        holder.tv_tihuo_all_order_lv.setTag(position);
        holder.tv_in_time.setText("入仓时间");
        holder.tv_trans_all_order_lv.setOnClickListener(this);
        holder.tv_tihuo_all_order_lv.setOnClickListener(this);
        PickUpList pickUp = list.get(position);
        holder.tv_cangdan.setText("提货编号");
        holder.tv_house_num_my_cangdan.setText(pickUp.getGetId());
        holder.tv_tihuo.setText("申请时间");
        holder.tv_success_all_order_lv.setText("待提货");
        holder.tv_name_lv_lv.setText(pickUp.getProductName());
//0：取消；1：等待提货；2，成功
            if (pickUp.getIsget().equals("1")) {
                //等待提货
                holder.tv_success_all_order_lv.setText("等待提货");
                holder.tv_tihuo_all_order_lv.setVisibility(View.VISIBLE);
                holder.tv_trans_all_order_lv.setVisibility(View.VISIBLE);
            } else if (pickUp.getIsget().equals("0")) {
                //已取消
                holder.tv_success_all_order_lv.setText("已取消");
                holder.tv_tihuo_all_order_lv.setVisibility(View.GONE);
                holder.tv_trans_all_order_lv.setVisibility(View.GONE);
            } else if (pickUp.getIsget().equals("2")) {
                //已取消
                holder.tv_success_all_order_lv.setText("已完成");
                holder.tv_tihuo_all_order_lv.setVisibility(View.GONE);
                holder.tv_trans_all_order_lv.setVisibility(View.GONE);
            }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_tihuo_all_order_lv:
                index = (int) v.getTag();
                getId = list.get(index).getGetId();
                showDailog();
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                dialog.dismiss();
                cancelPick();
                break;
            case R.id.tv_trans_all_order_lv:
                intent.setClass(activity, ModifyPickUpActivity.class);
                activity.startActivityForResult(intent, 1);
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
        TextView tv_house_num_my_cangdan;
        TextView tv_in_time_my_cangdan;
        TextView tv_trans_all_order_lv;
        TextView tv_tihuo_all_order_lv;
        TextView tv_in_time;
        TextView tv_tihuo;
        LinearLayout ll_right;
        TextView tv_cangdan;
        TextView tv_success_all_order_lv;

        ViewHolder(View view) {
            tv_success_all_order_lv = (TextView) view.findViewById(R.id.tv_success_all_order_lv);
            tv_tihuo = (TextView) view.findViewById(R.id.tv_tihuo);
            tv_cangdan = (TextView) view.findViewById(R.id.tv_cangdan);
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_type_lv_lv = (TextView) view.findViewById(R.id.tv_type_lv_lv);
            tv_grade_lv_lv = (TextView) view.findViewById(R.id.tv_grade_lv_lv);
            tv_address_lv_lv = (TextView) view.findViewById(R.id.tv_address_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv = (TextView) view.findViewById(R.id.tv_num_lv_lv);
            tv_house_num_my_cangdan = (TextView) view.findViewById(R.id.tv_house_num_my_cangdan);
            tv_in_time_my_cangdan = (TextView) view.findViewById(R.id.tv_in_time_my_cangdan);
            tv_trans_all_order_lv = (TextView) view.findViewById(R.id.tv_trans_all_order_lv);
            tv_tihuo_all_order_lv = (TextView) view.findViewById(R.id.tv_tihuo_all_order_lv);
            ll_right = (LinearLayout) view.findViewById(R.id.ll_right);
            tv_in_time = (TextView) view.findViewById(R.id.tv_in_time);
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
        tv_delete_order.setText("确认取消此提货单?");
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


    private String code = "3003";
    private String getId;

    //取消提货单
    public void cancelPick() {
        customProgress = CustomProgress.show(activity, "取消中···", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("resutl_quxiao", getId + "____" + DefineUtil.USERID + "_____" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    list.get(index).setIsget("0");
                    notifyDataSetChanged();
                } else {
                    AppUtils.toastText(activity, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, PickUpUrl.postCancelUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, getId), activity);
    }

}
