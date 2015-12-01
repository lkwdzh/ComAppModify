package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Buyer;

import java.util.List;

/**
 * Created by aglook on 2015/11/30.
 */
public class BuyerListAdapter extends BaseAdapter {
    private List<Buyer>list;
    private Context context;

    public BuyerListAdapter(List<Buyer> list, Context context) {
        this.list = list;
        this.context = context;
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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_driver_lv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        Buyer buyer = list.get(position);
        holder.cb_driver_lv.setVisibility(View.INVISIBLE);
        holder.tv_driver_lv.setText(buyer.getUserName());
        return convertView;
    }

    class ViewHolder{
        TextView tv_driver_lv;
        CheckBox cb_driver_lv;

        ViewHolder(View view) {
            tv_driver_lv=(TextView)view.findViewById(R.id.tv_driver_lv);
            cb_driver_lv=(CheckBox)view.findViewById(R.id.cb_driver_lv);
        }
    }

}
