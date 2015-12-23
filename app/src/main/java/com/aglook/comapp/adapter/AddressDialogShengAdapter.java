package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Sheng;

import java.util.List;

/**
 * Created by aglook on 2015/12/23.
 */
public class AddressDialogShengAdapter extends BaseAdapter {
    private List<Sheng>list;
    private Context context;

    public AddressDialogShengAdapter(List<Sheng> list, Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_dialog_address,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
        holder= (ViewHolder) convertView.getTag();
        }
        Sheng sheng = list.get(position);
        holder.tv_content_dialog.setText(sheng.getP());
        return convertView;
    }

    class  ViewHolder{
        TextView tv_content_dialog;

        ViewHolder(View view) {
            tv_content_dialog=(TextView)view.findViewById(R.id.tv_content_dialog);
        }
    }
}
