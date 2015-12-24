package com.aglook.comapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Activity.AddAddressActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Address;

import java.util.List;

/**
 * Created by aglook on 2015/12/23.
 */
public class AddressListAdapter extends BaseAdapter {
    private Activity context;
    private List<Address>list;

    private final int MODIFY_ADDRESS=2;

    public AddressListAdapter(Activity context, List<Address> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_address_list,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        Address address = list.get(position);
        holder.tv_name.setText(address.getUserName());
        holder.tv_phone.setText(address.getUserPhone());
        if (address.getDefaultFlag()!=null){
            if (address.getDefaultFlag().equals("1")){
                //默认
                holder.tv_isMoren.setVisibility(View.VISIBLE);
            }else {
                holder.tv_isMoren.setVisibility(View.GONE);
            }
        }

        if (address.isCheck()){
            holder.iv_isCheck.setVisibility(View.VISIBLE);
        }else {
            holder.iv_isCheck.setVisibility(View.GONE);
        }
        holder.tv_address.setText(address.getUserArea()+address.getUserAddress());
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到修改界面
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.putExtra("isModify",true);
                intent.putExtra("modifyAddress",list.get(position));
                context.startActivityForResult(intent,MODIFY_ADDRESS);
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView iv_isCheck;
        TextView tv_isMoren;
        TextView tv_address;
        ImageView iv_edit;
        TextView tv_name;
        TextView tv_phone;

        ViewHolder(View view) {
            tv_phone=(TextView)view.findViewById(R.id.tv_phone);
            tv_name=(TextView)view.findViewById(R.id.tv_name);
            iv_isCheck=(ImageView)view.findViewById(R.id.iv_isCheck);
            tv_isMoren=(TextView)view.findViewById(R.id.tv_isMoren);
            tv_address=(TextView)view.findViewById(R.id.tv_address);
            iv_edit=(ImageView)view.findViewById(R.id.iv_edit);
        }
    }
}
