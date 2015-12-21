package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.DriverList;

import java.util.List;

/**
 * Created by aglook on 2015/11/10.
 */
public class DriverListAdapter extends BaseAdapter {
    private Context context;
    private List<DriverList>list;
    private boolean canCheck;
    private CallBackData  callBackData;
    private int num;
    public DriverListAdapter(Context context, List<DriverList> list, boolean canCheck, CallBackData  callBackData) {
        this.context = context;
        this.list = list;
        this.canCheck = canCheck;
        this.callBackData=callBackData;
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
        final ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_driver_lv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (canCheck){
            holder.cb_driver_lv.setVisibility(View.VISIBLE);
            holder.cb_driver_lv.setBackgroundResource(R.drawable.ischecked);
        }else {
            holder.cb_driver_lv.setVisibility(View.GONE);
        }

        final DriverList driverList = list.get(position);

        holder.cb_driver_lv.setChecked(driverList.isChecked());
        holder.tv_driver_lv.setText(driverList.getUserName());
        holder.cb_driver_lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (holder.cb_driver_lv.isChecked()){
//                    list.get(position).setChecked(false);
//                }else {
//                    list.get(position).setChecked(true);
//                }
               list.get(position).setChecked(! list.get(position).isChecked());
                num=0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()){
                        num++;
                    }
                }
                notifyDataSetChanged();
                callBackData.callBack(num);
            }
        });
//        holder.ll_cb_d_lv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.cb_driver_lv.isChecked()){
//                    list.get(position).setChecked(false);
//                }else {
//                    list.get(position).setChecked(true);
//                }
////               list.get(position).setChecked(! list.get(position).isChecked());
//                num=0;
//                for (int i = 0; i < list.size(); i++) {
//                    if (list.get(i).isChecked()){
//                        num++;
//                    }
//                }
//                notifyDataSetChanged();
//                callBackData.callBack(num);
//            }
//        });
        return convertView;
    }

    class ViewHolder{
        TextView tv_driver_lv;
        CheckBox cb_driver_lv;
        LinearLayout ll_cb_d_lv;

        ViewHolder(View view) {
            tv_driver_lv=(TextView)view.findViewById(R.id.tv_driver_lv);
            cb_driver_lv=(CheckBox)view.findViewById(R.id.cb_driver_lv);
            ll_cb_d_lv=(LinearLayout)view.findViewById(R.id.ll_cb_d_lv);
        }
    }

    public interface CallBackData{
        public void callBack(int num);
    }
}
