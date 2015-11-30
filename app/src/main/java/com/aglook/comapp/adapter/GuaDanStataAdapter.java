package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.GuaDanStataLiL;
import com.aglook.comapp.view.Timestamp;

import java.util.List;

/**
 * Created by aglook on 2015/11/29.
 */
public class GuaDanStataAdapter extends BaseAdapter {
    private Context context;
    List<GuaDanStataLiL> list;

    public GuaDanStataAdapter(Context context, List<GuaDanStataLiL> list) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_gua_dan_state, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GuaDanStataLiL stataLiL = list.get(position);
        if (stataLiL.getOrderAtime()!=null&&!"".equals(stataLiL.getOrderAtime())) {
            holder.tv_xia_state.setText(Timestamp.getDateToDate(stataLiL.getOrderAtime()));
        }
        if (stataLiL.getOrderFtime()!=null&&!"".equals(stataLiL.getOrderFtime())) {
            holder.tv_over_state.setText(Timestamp.getDateToDate(stataLiL.getOrderFtime()));
        }
        holder.tv_weight_state.setText(stataLiL.getWeightUse() + "吨");
        if (stataLiL.getOrderState().equals("notpay")) {
            holder.tv_state_state.setText("待支付");
        } else if (stataLiL.getOrderState().equals("success")) {
            holder.tv_state_state.setText("支付成功");
        } else if (stataLiL.getOrderState().equals("close")) {
            holder.tv_state_state.setText("交易关闭");
        }
        holder.tv_seat_state.setText(stataLiL.getBuyUserSeat());
        holder.tv_shouxu_state.setText(stataLiL.getCounter());
        holder.tv_store_state.setText(stataLiL.getStorage());
        return convertView;
    }

    class ViewHolder {
        TextView tv_xia_state;
        TextView tv_over_state;
        TextView tv_seat_state;
        TextView tv_weight_state;
        TextView tv_state_state;
        TextView tv_shouxu_state;
        TextView tv_store_state;

        ViewHolder(View view) {
            tv_xia_state = (TextView) view.findViewById(R.id.tv_xia_state);
            tv_over_state = (TextView) view.findViewById(R.id.tv_over_state);
            tv_seat_state = (TextView) view.findViewById(R.id.tv_seat_state);
            tv_weight_state = (TextView) view.findViewById(R.id.tv_weight_state);
            tv_state_state = (TextView) view.findViewById(R.id.tv_state_state);
            tv_shouxu_state = (TextView) view.findViewById(R.id.tv_shouxu_state);
            tv_store_state = (TextView) view.findViewById(R.id.tv_store_state);
        }
    }



}
