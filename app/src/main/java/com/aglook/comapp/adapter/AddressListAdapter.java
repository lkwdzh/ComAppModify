package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/12/23.
 */
public class AddressListAdapter extends BaseAdapter {
    private Context context;

    public AddressListAdapter(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_address_list,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder{
        ImageView iv_isCheck;
        TextView tv_isMoren;
        TextView tv_address;
        ImageView iv_edit;

        ViewHolder(View view) {
            iv_isCheck=(ImageView)view.findViewById(R.id.iv_isCheck);
            tv_isMoren=(TextView)view.findViewById(R.id.tv_isMoren);
            tv_address=(TextView)view.findViewById(R.id.tv_address);
            iv_edit=(ImageView)view.findViewById(R.id.iv_edit);
        }
    }
}
