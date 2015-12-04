package com.aglook.comapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.AllOrderDataList;
import com.aglook.comapp.util.XBitmap;

import java.util.List;

/**
 * Created by aglook on 2015/11/10.
 */
public class OrderDetailAdapter extends BaseAdapter {
    private Context context;
    private List<AllOrderDataList>list;
    private boolean isSuccess;

    public OrderDetailAdapter(Context context, List<AllOrderDataList> list) {
        this.context = context;
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

    public void isSuccess(boolean isSuccess){
        this.isSuccess=isSuccess;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_all_order_lv_lv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        AllOrderDataList dataList = list.get(position);
        XBitmap.displayImage(holder.iv_lv_lv,dataList.getProductLogo(),context);
        holder.tv_name_lv_lv.setText(dataList.getProductName());
        holder.tv_price_lv_lv.setText(dataList.getProductMoney()+"");
        holder.tv_weight_lv_lv.setText(dataList.getWeightUseable()+"吨");
        holder.tv_num_lv_lv.setText(dataList.getProductMoneyYh()+"");
//        holder.tv_num_lv_lv.setText(dataList.getProductNum());

        if (!isSuccess){
            holder.tv_sell_all_order_lv.setVisibility(View.GONE);
            holder.tv_pick_all_order_lv.setVisibility(View.GONE);
        }else {
            holder.tv_sell_all_order_lv.setVisibility(View.VISIBLE);
            holder.tv_pick_all_order_lv.setVisibility(View.VISIBLE);
        }
        Log.d("isSuccess",isSuccess+"");
        return convertView;
    }

//    @Override
//    public boolean isEnabled(int position) {
//        return false;
//    }

    class ViewHolder{
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;
        TextView tv_sell_all_order_lv;
        TextView tv_pick_all_order_lv;
        ViewHolder(View view) {
            tv_pick_all_order_lv=(TextView)view.findViewById(R.id.tv_pick_all_order_lv);
            tv_sell_all_order_lv=(TextView)view.findViewById(R.id.tv_sell_all_order_lv);
            iv_lv_lv=(ImageView)view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv=(TextView)view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv=(TextView)view.findViewById(R.id.tv_price_lv_lv);
            tv_weight_lv_lv=(TextView)view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv=(TextView)view.findViewById(R.id.tv_num_lv_lv);
        }
    }
}
