package com.aglook.comapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.AllGuaDanActivity;
import com.aglook.comapp.Activity.AllOrderActivity;
import com.aglook.comapp.Activity.GuaDanAddActivity;
import com.aglook.comapp.Activity.PersonInformationActivity;
import com.aglook.comapp.Activity.SettingActivity;
import com.aglook.comapp.Activity.ToPayActivity;
import com.aglook.comapp.Activity.ToReceiveActivity;
import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/10/26.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_icon_mine_fragment;
    private RelativeLayout rl_background_mine_fragment;
    private LinearLayout ll_all_guadan_mine_fragment;
    private LinearLayout ll_yaoguadan_mine_fragment;
    private TextView tv_setting_mine_fragment;
    private LinearLayout ll_all_order_mine_fragment;
    private LinearLayout ll_to_pay_mine_fragment;
    private LinearLayout ll_to_receive_mine_fragment;

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
        ll_all_guadan_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_all_guadan_mine_fragment);
        ll_yaoguadan_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_yaoguadan_mine_fragment);
        tv_setting_mine_fragment = (TextView) view.findViewById(R.id.tv_setting_mine_fragment);
        ll_all_order_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_all_order_mine_fragment);
        ll_to_pay_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_to_pay_mine_fragment);
        ll_to_receive_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_to_receive_mine_fragment);
    }

    //点击事件
    public void click(){
//        iv_icon_mine_fragment.setOnClickListener(this);
        rl_background_mine_fragment.setOnClickListener(this);
        ll_all_guadan_mine_fragment.setOnClickListener(this);
        ll_yaoguadan_mine_fragment.setOnClickListener(this);
        tv_setting_mine_fragment.setOnClickListener(this);
        ll_all_order_mine_fragment.setOnClickListener(this);
        ll_to_pay_mine_fragment.setOnClickListener(this);
        ll_to_receive_mine_fragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_background_mine_fragment:
//                intent.setClass(getActivity(), LoginActivity.class);
                intent.setClass(getActivity(), PersonInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_all_guadan_mine_fragment:
                intent.setClass(getActivity(), AllGuaDanActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yaoguadan_mine_fragment:
                intent.setClass(getActivity(), GuaDanAddActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_setting_mine_fragment:
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_all_order_mine_fragment:
                intent.setClass(getActivity(), AllOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_to_pay_mine_fragment:
                intent.setClass(getActivity(), ToPayActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_to_receive_mine_fragment:
                intent.setClass(getActivity(), ToReceiveActivity.class);
                startActivity(intent);
                break;
        }
    }
}
