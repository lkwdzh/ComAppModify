package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aglook.comapp.Activity.OrderDetailActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.AllOrder;
import com.aglook.comapp.bean.AllOrderDataList;
import com.aglook.comapp.url.AllOrderUrl;
import com.aglook.comapp.url.PayUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.MyListView;
import com.aglook.comapp.view.Utility;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.List;

/**
 * Created by aglook on 2015/11/6.
 */
public class ToPayAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private List<AllOrder> list;
    private List<AllOrderDataList> sonList;
    private String orderId;
    private int index;
    private CustomProgress customProgress;
    private String money;

    public ToPayAdapter(Activity activity, List<AllOrder> list) {
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

    private ViewHolder holder;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.layout_all_order_lv, null);
            holder = new ViewHolder(convertView);
            holder.tv_delete_all_order_lv.setTag(position);
            holder.tv_click_all_order_lv.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_click_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_click_all_order_lv.setText("去支付");
        holder.tv_delete_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_delete_all_order_lv.setText("取消");
        holder.tv_click_all_order_lv.setOnClickListener(this);
        holder.tv_delete_all_order_lv.setOnClickListener(this);
        holder.lv_all_order_lv.setFocusable(false);

        AllOrder order = list.get(position);

        orderId = order.getOrderId();
        sonList = list.get(position).getOrderDateList();
        holder.adapter = new AllOrderLVAdapter(activity, sonList);
        holder.lv_all_order_lv.setAdapter(holder.adapter);
        Utility.setListViewHeightBasedOnChildren(holder.lv_all_order_lv);
        holder.adapter.notifyDataSetChanged();
        holder.tv_order_num_all_order_lv.setText(order.getOrderId());
        holder.tv_cost_all_order_lv.setText(order.getTotalFee());
        if (order.getOrderStatus().equals("notpay")) {
            holder.tv_success_all_order_lv.setText("待支付");
            holder.tv_success_all_order_lv.setTextColor(activity.getResources().getColor(R.color.green_356600));
        } else {
            holder.tv_success_all_order_lv.setText("交易成功");
            holder.tv_success_all_order_lv.setTextColor(activity.getResources().getColor(R.color.red_c91014));
        }

        holder.lv_all_order_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                Intent intent = new Intent(activity, OrderDetailActivity.class);
                intent.putExtra("AllOrder", list.get(position));
                activity.startActivity(intent);
            }
        });

        holder.tv_order_num_all_order_lv.setText(order.getOrderId());
        if (order.getOrderStatus().equals("notpay")) {
            holder.tv_success_all_order_lv.setText("待支付");
            holder.tv_success_all_order_lv.setTextColor(activity.getResources().getColor(R.color.green_356600));
        } else {
            holder.tv_success_all_order_lv.setText("交易成功");
            holder.tv_success_all_order_lv.setTextColor(activity.getResources().getColor(R.color.red_c91014));
        }
        holder.tv_money_all_order_lv.setText(order.getMoney() + "");
        holder.tv_order_total_all_order_lv.setText(order.getOrderDateList().size() + "");

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_click_all_order_lv:
                AppUtils.toastText(activity, "去支付");
                index=(int)v.getTag();
                money=String.valueOf(list.get(index).getMoney());
                orderId=list.get(index).getOrderId();
                intent.setAction("android.intent.action.VIEW");
                Uri uri=Uri.parse(PayUrl.postPay(orderId,DefineUtil.USERID,money,money));
//                Uri uri=Uri.parse("http://javacrazyer.iteye.com/blog/1840093");
                intent.setData(uri);
                activity.startActivity(intent);
                break;
            case R.id.tv_delete_all_order_lv:
                 index = (int) v.getTag();
                AppUtils.toastText(activity,index+"");
                orderId=list.get(index).getOrderId();
                showDailog();
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                //取消订单

                cancelOrder();
                dialog.dismiss();
                break;
        }
    }
    //zhifu
    public void pay(){
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result-pay",arg0.result);
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri uri=Uri.parse(payUrl);
//                intent.setData(uri);
//                activity.startActivity(intent);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.PAY, PayUrl.postPayWGUrl(orderId, DefineUtil.USERID, money, money),activity);
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
            tv_delete_all_order_lv = (TextView) view.findViewById(R.id.tv_delete_all_order_lv);
            adapter = new AllOrderLVAdapter(activity, sonList);
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
        tv_delete_order.setText("确认取消此定单?");
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


    public void cancelOrder() {
        customProgress=CustomProgress.show(activity,"",true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
                Log.d("result_cancel", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    //若成功，则刷新列表
                    list.remove(index);
                    notifyDataSetChanged();
                } else {
                    AppUtils.toastText(activity, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANCEL_ORDER, AllOrderUrl.postCancelOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderId), activity);
    }


}
