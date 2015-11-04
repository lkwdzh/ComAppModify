package com.aglook.comapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aglook.comapp.R;
import com.aglook.comapp.util.AppUtils;


/**
 * Created by aglook on 2015/10/27.
 */
public class RecycleHomePageAdapter extends RecyclerView.Adapter<RecycleHomePageAdapter.MyViewHolder> {
    private int imageArray[] = {R.drawable.liangshi, R.drawable.siliao, R.drawable.tongxun,
            R.drawable.youlei, R.drawable.youliao};
    private String nameArray[] = {"粮食", "饲料", "通讯", "油类", "油料"};
    private MyViewHolder mViewHoder;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_home_page_hor_listview, viewGroup, false);
        mViewHoder = new MyViewHolder(view);
        return mViewHoder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.iv_homepage_hor.setImageResource(imageArray[i]);
        myViewHolder.tv_home_page_hor.setText(nameArray[i]);
        mViewHoder.setPosition(i);
    }

    @Override
    public int getItemCount() {
        return 5;
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
            Toast.makeText(itemView.getContext(), "click " + mPosition, Toast.LENGTH_SHORT).show();
        }
    }

}
