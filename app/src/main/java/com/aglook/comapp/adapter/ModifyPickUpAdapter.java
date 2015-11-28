package com.aglook.comapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.Activity.DriverListActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.util.AppUtils;

/**
 * Created by aglook on 2015/11/28.
 */
public class ModifyPickUpAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity context;
    private int index;
    private CallBackData callBackData;

    public ModifyPickUpAdapter(Activity context, CallBackData callBackData) {
        this.context = context;
        this.callBackData=callBackData;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_modify_pick_up, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_modify_modify_pick_up.setTag(position);
        holder.tv_modify_modify_pick_up.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_modify_modify_pick_up:
                index = (int) v.getTag();
                AppUtils.toastText(context,index+"");
                intent.setClass(context, DriverListActivity.class);
                intent.putExtra("isModify", true);
                callBackData.callBackIndex(index);
                context.startActivityForResult(intent, 1);
                break;
        }
    }

    public interface CallBackData{
        public void callBackIndex(int index);
    }

    class ViewHolder {
        TextView tv_name_modify_pick_up;
        TextView tv_weight_modify_pick_up;
        TextView tv_type_modify_pick_up;
        TextView tv_modify_modify_pick_up;

        ViewHolder(View view) {
            tv_name_modify_pick_up = (TextView) view.findViewById(R.id.tv_name_modify_pick_up);
            tv_weight_modify_pick_up = (TextView) view.findViewById(R.id.tv_weight_modify_pick_up);
            tv_type_modify_pick_up = (TextView) view.findViewById(R.id.tv_type_modify_pick_up);
            tv_modify_modify_pick_up = (TextView) view.findViewById(R.id.tv_modify_modify_pick_up);
        }
    }
}
