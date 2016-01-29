package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.url.SettingUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class PersonInformationActivity extends BaseActivity {


    private TextView tv_basic_infor_peson_info;
    private TextView tv_bang_card_person_info;
    private TextView tv_linkman_person_info;
    private TextView tv_driver_person_info;
    private TextView tv_friend_person_info;
    private Login login;
    private TextView tv_name_person_info;
    private ComAppApplication comAppApplication;
    private TextView tv_address_person_info;
    private TextView tv_bill_person_info;
    private Button btn_tuichu;
    private CustomProgress customProgress;
    private LinearLayout ll_faPiao;

    @Override
    public void initView() {
        setContentView(R.layout.activity_person_information);
        setTitleBar("个人信息");
        comAppApplication = (ComAppApplication) getApplication();
//        ExitApplication.getInstance().addActivity(this);
        init();
        fillData();
        click();
    }

    public void init() {
        login = comAppApplication.getLogin();
        tv_basic_infor_peson_info = (TextView) findViewById(R.id.tv_basic_infor_peson_info);
        tv_bang_card_person_info = (TextView) findViewById(R.id.tv_bang_card_person_info);
        tv_linkman_person_info = (TextView) findViewById(R.id.tv_linkman_person_info);
        tv_driver_person_info = (TextView) findViewById(R.id.tv_driver_person_info);
        tv_friend_person_info = (TextView) findViewById(R.id.tv_friend_person_info);
        tv_name_person_info = (TextView) findViewById(R.id.tv_name_person_info);
        tv_address_person_info = (TextView) findViewById(R.id.tv_address_person_info);
        tv_bill_person_info = (TextView) findViewById(R.id.tv_bill_person_info);
        ll_faPiao = (LinearLayout) findViewById(R.id.ll_faPiao);
        btn_tuichu = (Button) findViewById(R.id.btn_tuichu);
        if (login.getPshUser().getUserType()==2){
            ll_faPiao.setVisibility(View.GONE);
        }else {
            ll_faPiao.setVisibility(View.VISIBLE);
        }
    }

    public void click() {
        tv_basic_infor_peson_info.setOnClickListener(this);
        tv_bang_card_person_info.setOnClickListener(this);
        tv_linkman_person_info.setOnClickListener(this);
        tv_driver_person_info.setOnClickListener(this);
        tv_friend_person_info.setOnClickListener(this);
        tv_address_person_info.setOnClickListener(this);
        tv_bill_person_info.setOnClickListener(this);
        btn_tuichu.setOnClickListener(this);
    }

    public void fillData() {
        if (login != null) {
            tv_name_person_info.setText(login.getPshUser().getUsername());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        login = comAppApplication.getLogin();
        fillData();
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_basic_infor_peson_info:
                if (login.getPshUser().getUserType() == 2) {
                    //个人
                    intent.setClass(PersonInformationActivity.this, BasicInformationActivity.class);
                } else {
                    // 公司
                    intent.setClass(PersonInformationActivity.this, CompanyInfoActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.tv_bang_card_person_info:
                intent.setClass(PersonInformationActivity.this, CardListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_driver_person_info:
                intent.setClass(PersonInformationActivity.this, DriverListActivity.class);
                intent.putExtra("canCheck", false);
                startActivity(intent);
                break;
            case R.id.tv_linkman_person_info:
                intent.setClass(PersonInformationActivity.this, FriendsListActivity.class);
                intent.putExtra("buyOrLink", false);
                startActivity(intent);
                break;
            case R.id.tv_friend_person_info:
                intent.setClass(PersonInformationActivity.this, GoodsCollectActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_address_person_info:
                intent.setClass(PersonInformationActivity.this, AddressListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_bill_person_info:
                intent.setClass(PersonInformationActivity.this, BillActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tuichu:
                loginOut();
                break;
        }
    }

    //退出
    public void loginOut() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_login_out",arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    //成功退出
                    comAppApplication.setLogin(null);
                    PersonInformationActivity.this.finish();
                    DefineUtil.USERID=null;
                    DefineUtil.TOKEN=null;
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePostUp(DefineUtil.LOGIN_OUT, SettingUrl.postLogin_out_url(DefineUtil.USERID), PersonInformationActivity.this);
    }

}
