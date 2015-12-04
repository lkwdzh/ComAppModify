package com.aglook.comapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Activity.GuaDanAddActivity;
import com.aglook.comapp.Activity.PickInfoActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.AllOrderDataList;
import com.aglook.comapp.util.XBitmap;

import java.util.List;

/**
 * Created by aglook on 2015/11/6.
 */
public class AllOrderLVAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<AllOrderDataList> list;
    private boolean isSuccess;
    private int index;

    public AllOrderLVAdapter(Context context, List<AllOrderDataList> list,boolean isSuccess) {
        this.context = context;
        this.list = list;
        this.isSuccess=isSuccess;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_all_order_lv_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_sell_all_order_lv.setTag(position);
        holder.tv_pick_all_order_lv.setTag(position);
        holder.tv_sell_all_order_lv.setOnClickListener(this);
        holder.tv_pick_all_order_lv.setOnClickListener(this);

        AllOrderDataList dataList = list.get(position);
//        Log.d("result_list_adad",position+"_______+"+list.size()+"_____"+list);
        XBitmap.displayImage(holder.iv_lv_lv, dataList.getProductLogo(), context);
        holder.tv_name_lv_lv.setText(dataList.getProductName());
        holder.tv_price_lv_lv.setText(dataList.getProductMoney()+"");
        holder.tv_weight_lv_lv.setText(dataList.getWeightUseable()+"吨");
        holder.tv_num_lv_lv.setText(dataList.getProductMoneyYh()+"");
        if (isSuccess){
            holder.tv_sell_all_order_lv.setVisibility(View.GONE);
            holder.tv_pick_all_order_lv.setVisibility(View.GONE);
        }else {
            holder.tv_sell_all_order_lv.setVisibility(View.VISIBLE);
            holder.tv_pick_all_order_lv.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_pick_all_order_lv:
                index = (int) v.getTag();
                //提货
                intent.setClass(context, PickInfoActivity.class);
                intent.putExtra("code","2002");
                intent.putExtra("isPlate",true);
                intent.putExtra("orderdataId",list.get(index).getOrderdataId());
                intent.putExtra("originalListId",list.get(index).getProductListId());
                context.startActivity(intent);
                break;
            case R.id.tv_sell_all_order_lv:
                index=(int)v.getTag();
                //转售
                intent.setClass(context, GuaDanAddActivity.class);
                intent.putExtra("code","2002");
                intent.putExtra("codeGua","2003");
                intent.putExtra("isPlate",true);
                intent.putExtra("orderdataId",list.get(index).getOrderdataId());
                intent.putExtra("originalListId",list.get(index).getProductListId());
                context.startActivity(intent);
                break;
        }
    }


    class ViewHolder {
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
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv = (TextView) view.findViewById(R.id.tv_num_lv_lv);
        }
    }
}
