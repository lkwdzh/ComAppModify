package com.aglook.comapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aglook.comapp.Activity.GoodsDetailActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.HomePage;
import com.aglook.comapp.bean.HomePageList;
import com.aglook.comapp.view.MyGridView;

import java.util.List;

/**
 * Created by aglook on 2015/11/30.
 */
public class HomePageEXGridView extends BaseExpandableListAdapter {
    private List<HomePage> list;
    private Context context;
    private List<HomePageList> sonList;

    public HomePageEXGridView(List<HomePage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    class GroupViewHolder {
        TextView tv_name_group;

        GroupViewHolder(View view) {
            tv_name_group = (TextView) view.findViewById(R.id.tv_name_group);
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_home_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        HomePage homePage = list.get(groupPosition);
        groupViewHolder.tv_name_group.setText(homePage.getCategoryName());

        return convertView;
    }

    class ChildViewHolder {
        MyGridView gv_homepage;
        HomePageGridViewAdapter adapter;

        ChildViewHolder(View view) {
            gv_homepage = (MyGridView) view.findViewById(R.id.gv_homepage);
            adapter = new HomePageGridViewAdapter(context, sonList);
        }

    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_home_son, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        sonList = list.get(groupPosition).getList();
        holder.adapter = new HomePageGridViewAdapter(context, sonList);
        holder.gv_homepage.setAdapter(holder.adapter);
        holder.gv_homepage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                List<HomePageList> listList = list.get(groupPosition).getList();
                intent.putExtra("productId", listList.get(position).getProductId());
                intent.putExtra("pointUser", listList.get(position).getIsAppoint());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
