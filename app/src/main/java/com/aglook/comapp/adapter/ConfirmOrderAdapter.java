package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.util.XBitmap;

import java.util.List;

/**
 * Created by aglook on 2015/11/6.
 */
public class ConfirmOrderAdapter extends BaseAdapter {
    private Context context;
    private List<ShoppingCart> list;

    public ConfirmOrderAdapter(Context context, List<ShoppingCart> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_confirm_order_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ShoppingCart shoppingCart = list.get(position);
        XBitmap.displayImage(holder.iv_confirm_order_lv, shoppingCart.getProductLogo(), context);
        holder.tv_name_confirm_order_lv.setText(shoppingCart.getProductName());
        holder.tv_num_confirm_order_lv.setText(shoppingCart.getProductNum() + "");
        holder.tv_price_confirm_order_lv.setText(shoppingCart.getProductMoney() + "");

        holder.tv_cost_confirm_order_lv.setText(shoppingCart.getCostMoney() + "");
        return convertView;
    }


    class ViewHolder {
        ImageView iv_confirm_order_lv;
        TextView tv_name_confirm_order_lv;
        TextView tv_price_confirm_order_lv;
        TextView tv_num_confirm_order_lv;
        TextView tv_cost_confirm_order_lv;

        ViewHolder(View view) {
            iv_confirm_order_lv = (ImageView) view.findViewById(R.id.iv_confirm_order_lv);
            tv_name_confirm_order_lv = (TextView) view.findViewById(R.id.tv_name_confirm_order_lv);
            tv_price_confirm_order_lv = (TextView) view.findViewById(R.id.tv_price_confirm_order_lv);
            tv_num_confirm_order_lv = (TextView) view.findViewById(R.id.tv_num_confirm_order_lv);
            tv_cost_confirm_order_lv = (TextView) view.findViewById(R.id.tv_cost_confirm_order_lv);
        }
    }


}
