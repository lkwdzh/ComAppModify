package com.aglook.comapp.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.DriverList;

import java.util.List;

/**
 * Created by aglook on 2015/11/10.
 */
public class PickInfoAdapter extends BaseAdapter {
    private Context context;
    private List<DriverList> list;

    public PickInfoAdapter(Context context, List<DriverList> list) {
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_pick_info, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final DriverList driverList = list.get(position);
        holder.tv_pick_info.setText(driverList.getUserName());
        //获取填写的信息
        holder.et_weighr_pick_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                String s1 = s.toString();
//                driverList.setWeight(s1);
                String temp = s.toString();
                int d = temp.indexOf(".");
                if (d < 0) {
//                if (temp.length() > 3) {
//
//                    s.delete(3, 4);
//                }
                    return;
                }
                if (temp.length() - d - 1 > 2) {
                    s.delete(d + 3, d + 4);
                } else if (d == 0) {
                    s.delete(d, d + 1);
                }
                driverList.setWeight(holder.et_weighr_pick_info.getText().toString());
                Log.d("result_driver",temp+"____"+driverList.getWeight());
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_pick_info;
        EditText et_weighr_pick_info;

        ViewHolder(View view) {
            tv_pick_info = (TextView) view.findViewById(R.id.tv_pick_info);
            et_weighr_pick_info = (EditText) view.findViewById(R.id.et_weighr_pick_info);
        }
    }
}
