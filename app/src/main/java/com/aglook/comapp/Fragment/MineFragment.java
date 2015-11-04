package com.aglook.comapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aglook.comapp.Activity.BasicInformationActivity;
import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/10/26.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_icon_mine_fragment;
    private RelativeLayout rl_background_mine_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.layout_mine_fragment,null);
       initView(view);
        click();
        return view;
    }

    //初始化控件
    public void initView(View view){
        iv_icon_mine_fragment = ((ImageView) view.findViewById(R.id.iv_icon_mine_fragment));
        rl_background_mine_fragment = (RelativeLayout) view.findViewById(R.id.rl_background_mine_fragment);
    }

    //点击事件
    public void click(){
//        iv_icon_mine_fragment.setOnClickListener(this);
        rl_background_mine_fragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_background_mine_fragment:
//                intent.setClass(getActivity(), LoginActivity.class);
                intent.setClass(getActivity(), BasicInformationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
