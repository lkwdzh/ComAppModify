package com.aglook.comapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.util.AppUtils;

import java.util.List;

/**
 * Created by aglook on 2015/11/2.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;
    private List<ShoppingCart> list;
    private CallBackData callBackData;
    private int num = 0;
    private double total = 0;

    public ShoppingCartAdapter(Context context, List<ShoppingCart> list, CallBackData callBackData) {
        this.context = context;
        this.list = list;
//        if (context instanceof CallBackData){
//            callBackData=(CallBackData)context;
//        }
        this.callBackData = callBackData;
    }

    @Override
    public int getCount() {
        return 15;
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_shoppring_cart_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ShoppingCart shoppingCart = list.get(position);

        holder.tv_count_shopping_cart_listview.setText(shoppingCart.getNum() + "");
        holder.cb_shopping_cart_listview.setChecked(shoppingCart.isChecked());
        holder.tv_price_shopping_cart_listview.setText("￥" + shoppingCart.getPrice());

        //点击增加按钮
        holder.iv_add_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num2 = Integer.parseInt(AppUtils.toStringTrim_TV(holder.tv_count_shopping_cart_listview));
                num2++;
                shoppingCart.setNum(num2);
                holder.tv_count_shopping_cart_listview.setText(num2 + "");

                double total = num2 * shoppingCart.getPrice();
                holder.tv_total_shopping_cart_listview.setText(total + "");
                shoppingCart.setTotal(total);

                dataChange();
            }
        });

//        点击减少按钮
        holder.iv_cut_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(AppUtils.toStringTrim_TV(holder.tv_count_shopping_cart_listview));
                if (num1 > 0) {
                    num1--;
                } else {
                    num1 = 0;
                }
                shoppingCart.setNum(num1);
                holder.tv_count_shopping_cart_listview.setText(num1 + "");
                double total = num1 * shoppingCart.getPrice();
                holder.tv_total_shopping_cart_listview.setText(total + "");
                shoppingCart.setTotal(total);

                dataChange();
            }
        });

        holder.cb_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCart.setChecked(!shoppingCart.isChecked());
                dataChange();
            }
        });

        for (int i = 0; i <list.size() ; i++) {
            Log.d("isCheck",i+"----"+list.get(i).isChecked());
        }
        return convertView;
    }


    public void dataChange() {
        num = 0;
        total = 0;
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).isChecked()) {
                num += list.get(i).getNum();
                total += list.get(i).getTotal();
            }
        }
        notifyDataSetChanged();
        callBackData.callBack(num, total);
    }

    class ViewHolder {
        ImageView iv_cut_shopping_cart_listview;
        ImageView iv_add_shopping_cart_listview;
        CheckBox cb_shopping_cart_listview;
        ImageView iv_shopping_cart_listview;
        TextView tv_name_shopping_cast_listview;
        TextView tv_price_shopping_cart_listview;
        TextView tv_count_shopping_cart_listview;
        TextView tv_total_shopping_cart_listview;

        ViewHolder(View view) {
            cb_shopping_cart_listview = (CheckBox) view.findViewById(R.id.cb_shopping_cart_listview);
            iv_cut_shopping_cart_listview = (ImageView) view.findViewById(R.id.iv_cut_shopping_cart_listview);
            iv_add_shopping_cart_listview = (ImageView) view.findViewById(R.id.iv_add_shopping_cart_listview);
            iv_shopping_cart_listview = (ImageView) view.findViewById(R.id.iv_shopping_cart_listview);
            tv_name_shopping_cast_listview = (TextView) view.findViewById(R.id.tv_name_shopping_cast_listview);
            tv_price_shopping_cart_listview = (TextView) view.findViewById(R.id.tv_price_shopping_cart_listview);
            tv_count_shopping_cart_listview = (TextView) view.findViewById(R.id.tv_count_shopping_cart_listview);
            tv_total_shopping_cart_listview = (TextView) view.findViewById(R.id.tv_total_shopping_cart_listview);
        }
    }

    public interface CallBackData {
        public void callBack(int num, double total);
    }
}
