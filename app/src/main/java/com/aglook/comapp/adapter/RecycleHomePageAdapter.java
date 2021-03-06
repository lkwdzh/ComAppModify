package com.aglook.comapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Activity.ZiXunListActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Information;

import java.util.List;


/**
 * Created by aglook on 2015/10/27.
 */
public class RecycleHomePageAdapter extends RecyclerView.Adapter<RecycleHomePageAdapter.MyViewHolder> {
//    private int imageArray[] = {R.drawable.liangshi, R.drawable.siliao, R.drawable.tongxun,
//            R.drawable.youlei, R.drawable.youliao};
//    private String nameArray[] = {"专业分析", "行情资讯", "专题报告", "最新公告", "网站公告"};
    private MyViewHolder mViewHoder;
    private Context context;
    private List<Information>list;

    public RecycleHomePageAdapter(Context context, List<Information> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_home_page_hor_listview, viewGroup, false);
        mViewHoder = new MyViewHolder(view);
        return mViewHoder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        if (i==0){
            myViewHolder.iv_homepage_hor.setVisibility(View.VISIBLE);
            myViewHolder.iv_homepage_hor.setImageResource(R.drawable.fenxi);
        }else {
            myViewHolder.iv_homepage_hor.setVisibility(View.GONE);
        }
        Information information = list.get(i);
        myViewHolder.tv_home_page_hor.setText(information.getClassName());
        mViewHoder.setPosition(i);
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_home_page_hor;
        private ImageView iv_homepage_hor;
        private int mPosition;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            iv_homepage_hor = (ImageView) itemView.findViewById(R.id.iv_homepage_hor);
            tv_home_page_hor = (TextView) itemView.findViewById(R.id.tv_home_page_hor);
        }

        public void setPosition(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
//            AppUtils.toastText(context,mPosition+"");
            Intent intent = new Intent();
            intent.setClass(context, ZiXunListActivity.class);
            intent.putExtra("classId",list.get(mPosition).getClassId());
            intent.putExtra("className",list.get(mPosition).getClassName());
            context.startActivity(intent);
        }
    }

}
