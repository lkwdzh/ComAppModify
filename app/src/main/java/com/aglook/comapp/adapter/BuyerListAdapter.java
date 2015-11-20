package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.LinkMan;

import java.util.List;

/**
 * Created by aglook on 2015/11/12.
 */
public class BuyerListAdapter extends BaseAdapter {
    private Context context;
    private boolean isBuyer;
    private List<LinkMan>list;
    private CallBackData  callBackData;
    private int num;

    public BuyerListAdapter(Context context, boolean isBuyer,List<LinkMan>list, CallBackData  callBackData) {
        this.context = context;
        this.isBuyer = isBuyer;
        this.list=list;
        this.callBackData=callBackData;
    }

    @Override
    public int getCount() {
        return 11;
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

        if (isBuyer){
            holder.cb_driver_lv.setBackgroundResource(R.drawable.ischecked);
        }else {
            holder.cb_driver_lv.setBackgroundResource(R.drawable.star_checked);
        }

        final LinkMan linkMan = list.get(position);
        holder.tv_driver_lv.setText(linkMan.getName());
        holder.cb_driver_lv.setChecked(linkMan.isChecked());

        holder.cb_driver_lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkMan.setChecked(!linkMan.isChecked());
                num=0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()){
                        num++;
                    }
                }
                callBackData.callBack(num);
            }
        });

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

    public interface CallBackData{
        public void callBack(int num);
    }

}
