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
import com.aglook.comapp.Activity.LoginActivity;
import com.aglook.comapp.Activity.MessageActivity;
import com.aglook.comapp.Activity.MyCangDanActivity;
import com.aglook.comapp.Activity.PersonInformationActivity;
import com.aglook.comapp.Activity.PickUpActivity;
import com.aglook.comapp.Activity.PlatformActivity;
import com.aglook.comapp.Activity.SettingActivity;
import com.aglook.comapp.Activity.TradeingActivity;
import com.aglook.comapp.Activity.TransSucceedActivity;
import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;

/**
 * Created by aglook on 2015/10/26.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_icon_mine_fragment;
    private RelativeLayout rl_background_mine_fragment;
    private LinearLayout ll_all_guadan_mine_fragment;
    private TextView tv_setting_mine_fragment;
    private LinearLayout ll_all_order_mine_fragment;
    private LinearLayout ll_to_pay_mine_fragment;
    private LinearLayout ll_to_receive_mine_fragment;
    private LinearLayout ll_my_cangdan_mine_fragment;
    private LinearLayout ll_pingtaicangdan_mine_fragment;
    private LinearLayout ll_tihuo_mine_fragment;
    private ComAppApplication comAppApplication;
    private Login login;
    private LinearLayout ll_jiaoyizhong_mine_fragment;
    private LinearLayout ll_jiaoyichenggong_mine_fragment;
    private TextView tv_name_mine_fragment;
    private TextView tv_all_order_point;
    private TextView tv_daifukuan_point;
    private TextView tv_daishouhuo_point;
    private TextView tv_all_guadan_point;
    private TextView tv_jiaoyizhong_point;
    private TextView tv_jiaoyichenggong_point;
    private TextView tv_cangdan_point;
    private TextView tv_pingtaicangdan_point;
    private TextView tv_tihuo_point;
    private TextView tv_message_mine_fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_mine_fragment, null);
        initView(view);
        click();
        fillData();
        return view;
    }

    //初始化控件
    public void initView(View view) {
        comAppApplication = (ComAppApplication) getActivity().getApplication();
        login = comAppApplication.getLogin();
        iv_icon_mine_fragment = ((ImageView) view.findViewById(R.id.iv_icon_mine_fragment));
        rl_background_mine_fragment = (RelativeLayout) view.findViewById(R.id.rl_background_mine_fragment);
        ll_all_guadan_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_all_guadan_mine_fragment);
        tv_setting_mine_fragment = (TextView) view.findViewById(R.id.tv_setting_mine_fragment);
        ll_all_order_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_all_order_mine_fragment);
        ll_to_pay_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_to_pay_mine_fragment);
        ll_to_receive_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_to_receive_mine_fragment);
        ll_my_cangdan_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_my_cangdan_mine_fragment);
        ll_pingtaicangdan_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_pingtaicangdan_mine_fragment);
        ll_tihuo_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_tihuo_mine_fragment);
        ll_jiaoyizhong_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_jiaoyizhong_mine_fragment);
        ll_jiaoyichenggong_mine_fragment = (LinearLayout) view.findViewById(R.id.ll_jiaoyichenggong_mine_fragment);
        tv_name_mine_fragment = (TextView) view.findViewById(R.id.tv_name_mine_fragment);
        tv_message_mine_fragment = (TextView) view.findViewById(R.id.tv_message_mine_fragment);

        //红点
        tv_all_order_point = (TextView) view.findViewById(R.id.tv_all_order_point);
        tv_daifukuan_point = (TextView) view.findViewById(R.id.tv_daifukuan_point);
        tv_daishouhuo_point = (TextView) view.findViewById(R.id.tv_daishouhuo_point);
        tv_all_guadan_point = (TextView) view.findViewById(R.id.tv_all_guadan_point);
        tv_jiaoyizhong_point = (TextView) view.findViewById(R.id.tv_jiaoyizhong_point);
        tv_jiaoyichenggong_point = (TextView) view.findViewById(R.id.tv_jiaoyichenggong_point);
        tv_cangdan_point = (TextView) view.findViewById(R.id.tv_cangdan_point);
        tv_pingtaicangdan_point = (TextView) view.findViewById(R.id.tv_pingtaicangdan_point);
        tv_tihuo_point = (TextView) view.findViewById(R.id.tv_tihuo_point);
    }

    //点击事件
    public void click() {
//        iv_icon_mine_fragment.setOnClickListener(this);
        rl_background_mine_fragment.setOnClickListener(this);
        ll_all_guadan_mine_fragment.setOnClickListener(this);
        tv_setting_mine_fragment.setOnClickListener(this);
        ll_all_order_mine_fragment.setOnClickListener(this);
        ll_to_pay_mine_fragment.setOnClickListener(this);
        ll_to_receive_mine_fragment.setOnClickListener(this);
        ll_my_cangdan_mine_fragment.setOnClickListener(this);
        ll_pingtaicangdan_mine_fragment.setOnClickListener(this);
        ll_tihuo_mine_fragment.setOnClickListener(this);
        ll_jiaoyizhong_mine_fragment.setOnClickListener(this);
        ll_jiaoyichenggong_mine_fragment.setOnClickListener(this);
        tv_message_mine_fragment.setOnClickListener(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1) {
            login = comAppApplication.getLogin();
            fillData();
        }
    }

    //填充数据
    public void fillData() {
        if (login != null) {
            tv_name_mine_fragment.setText(login.getPshUser().getUsername());

            tv_all_order_point.setVisibility(View.VISIBLE);
            tv_daifukuan_point.setVisibility(View.VISIBLE);
            tv_daishouhuo_point.setVisibility(View.VISIBLE);
            tv_all_guadan_point.setVisibility(View.VISIBLE);
            tv_jiaoyizhong_point.setVisibility(View.VISIBLE);
            tv_jiaoyichenggong_point.setVisibility(View.VISIBLE);
            tv_cangdan_point.setVisibility(View.VISIBLE);
            tv_pingtaicangdan_point.setVisibility(View.VISIBLE);
            tv_tihuo_point.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_background_mine_fragment:
                //判断是否已经登录，若已登录，则跳转到个人信息界面，若没有，则跳转到登录界面
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
//                Log.d("result_login_mine",comAppApplication.getLogin().toString());
                    intent.setClass(getActivity(), PersonInformationActivity.class);
                    intent.putExtra("login", login);
                    startActivity(intent);
                }
                break;
            case R.id.ll_all_guadan_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), AllGuaDanActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_jiaoyizhong_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), TradeingActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_setting_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), SettingActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_all_order_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), AllOrderActivity.class);
                    intent.putExtra("status",1);
                    startActivity(intent);
                }
                break;
            case R.id.ll_to_pay_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), AllOrderActivity.class);
                    intent.putExtra("status",2);
                    startActivity(intent);
                }
                break;
            case R.id.ll_to_receive_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), AllOrderActivity.class);
                    intent.putExtra("isSuccess",true);
                    intent.putExtra("status",3);
                    startActivity(intent);
                }
                break;
            case R.id.ll_my_cangdan_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), MyCangDanActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_pingtaicangdan_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), PlatformActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_tihuo_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), PickUpActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_jiaoyichenggong_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), TransSucceedActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_message_mine_fragment:
                if (login == null || "".equals(login)) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), MessageActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }


}
