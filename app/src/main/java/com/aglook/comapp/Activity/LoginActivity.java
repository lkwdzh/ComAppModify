package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.SpinnerAdapter;
import com.aglook.comapp.bean.AllOrder;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.url.AllOrderUrl;
import com.aglook.comapp.url.LoginUrl;
import com.aglook.comapp.url.ShoppingCartUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.SharedPreferencesUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity {


    private String str[] = {"用户名：", "席位号："};
    //    private Spinner spinner;
//    private ArrayAdapter<String> adapter;
    private Button btn_login;
    private String accountType;
    private String userName;
    private String password;
    private EditText et_username_login;
    private EditText et_password_login;
    private Login login;
    private ComAppApplication comAppApplication;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;
    private LinearLayout ll_top;
    private int totalNum;
    private int index;
    private SpinnerAdapter adapter;
//    private TextView tv_name;
    private TextView tv_register;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private TextView tv_find_password;
    private final int LOGIN_TO_REGISTER = 0;
    private final int LOGIN_TO_FINDPWD = 1;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        setTitleBar("登录");
//        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        comAppApplication = (ComAppApplication) getApplication();
        mShare = getSharedPreferences("une_pwd", LoginActivity.this.MODE_PRIVATE);
        mEditor = mShare.edit();
        adapter = new SpinnerAdapter(this, str);
        btn_login = (Button) findViewById(R.id.btn_login);
//        tv_name = (TextView) findViewById(R.id.tv_name);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        et_username_login = (EditText) findViewById(R.id.et_username_login);
        et_password_login = (EditText) findViewById(R.id.et_password_login);
        accountType = mShare.getString("accountType", "");
        if (accountType == null || "".equals(accountType)) {
            accountType = "1";
        }
//        tv_name.setText("用户名：");
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_find_password = (TextView) findViewById(R.id.tv_find_password);
        fillData();
    }

    public void fillData() {
//        accountType=mShare.getString("accountType","");
//        if (accountType.equals("0")) {
//            et_username_login.setText(mShare.getString("setName", ""));
//            tv_name.setText("席位号：");
//            index = 1;
//        } else {
//            tv_name.setText("用户名：");
            et_username_login.setText(mShare.getString("userName", ""));
//            index = 0;
//        }
//        adapter.setSelectItem(index);
        et_password_login.setText(mShare.getString("password", ""));
    }

    public void click() {
        tv_find_password.setOnClickListener(this);
        btn_login.setOnClickListener(this);
//        tv_name.setOnClickListener(this);
        ll_top.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    InputMethodManager imm = (InputMethodManager) LoginActivity.this
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                ll_top.getApplicationWindowToken(), 0);

                    }
                }
                return false;
            }
        });
        tv_register.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_login:
                userName = AppUtils.toStringTrim_ET(et_username_login);
                password = AppUtils.toStringTrim_ET(et_password_login);
//                if (accountType.equals("1")) {
                    mEditor.putString("userName", userName);
//                } else {
//                    mEditor.putString("setName", userName);
//                }
                mEditor.putString("accountType", accountType);
                mEditor.putString("password", password);
                mEditor.commit();
                if (userName == null || "".equals(userName)) {
                    AppUtils.toastText(LoginActivity.this, "账号不能为空");
                    return;
                }

                if (password == null || "".equals(password)) {
                    AppUtils.toastText(LoginActivity.this, "密码不能为空");
                    return;
                }
                login();
                break;
//            case R.id.tv_name:
//                showWindow(view);
//                break;
            case R.id.tv_register:
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, LOGIN_TO_REGISTER);
                break;
            case R.id.tv_find_password:
                intent.setClass(LoginActivity.this, FindPasswordActivity.class);
                startActivityForResult(intent, LOGIN_TO_FINDPWD);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_TO_REGISTER && resultCode == RESULT_OK) {
            userName = data.getStringExtra("username");
            password = data.getStringExtra("password");
            et_username_login.setText(userName);
            et_password_login.setText(password);
        } else if (requestCode == LOGIN_TO_FINDPWD && resultCode == RESULT_OK) {
            password = data.getStringExtra("password");
            et_password_login.setText(password);
        }
    }

    //登录账户
    public void login() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_login", accountType + "_______" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String str = JsonUtils.getJsonParam(arg0.result, "obj");
                login = JsonUtils.parse(str, Login.class);
                if (status.equals("1")) {//登录成功,跳转页面
                    DefineUtil.TOKEN = login.getToken();
                    DefineUtil.USERID = login.getPshUser().getUserId();
                    DefineUtil.BANKBAND = login.isBankBind();
//                    Log.d("result_login", DefineUtil.BANKBAND+"");
                    //登录成功，存储自动登录
                    SharedPreferencesUtils.saveBoolean(LoginActivity.this,"auto_login",true);

                    //判断是否绑定银行卡，并且是否是兴业银行
                    if (login.getBankCard()!=null&&!"".equals(login.getBankCard())){
                        if ("兴业银行".equals(login.getBankCard().getBankAlis())){
                            login.setXingYe(true);
                        }else {
                            login.setXingYe(false);
                        }
                    }else {
                        login.setXingYe(false);
                    }
                    comAppApplication.setLogin(login);
                    // 发送广播给MainActivity
                    //如果真实姓名，身份证与邮箱有一个为空，则去个人信息页面完善
                    if ((login.getPshUser().getUserNumber() == null || "".equals(login.getPshUser().getUserNumber()))) {
                        infoDialog();
                    } else {

                        LoginActivity.this.setResult(1);
                        LoginActivity.this.finish();
                    }
                    getCartListData();
                    getNotPayData();
                }else {
                    AppUtils.toastTextNew(LoginActivity.this,message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePostCheck(DefineUtil.LOGIN_IN, LoginUrl.postLonginUrl(userName, password, accountType, DefineUtil.DEVICE_NUM), LoginActivity .this);
    }


    //登录后跳转去完善信息的dialog
    public void infoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("您的信息尚未完善，请先完善信息");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //如果是个人
                if (login.getPshUser().getUserType() == 2) {
                    Intent intent = new Intent(LoginActivity.this, BasicInformationActivity.class);
                    LoginActivity.this.setResult(1);
                    LoginActivity.this.finish();
                    startActivity(intent);

                } else if (login.getPshUser().getUserType() == 1) {
                    //跳转到公司界面
                    Intent intent = new Intent(LoginActivity.this, CompanyInfoActivity.class);
                    LoginActivity.this.setResult(1);
                    LoginActivity.this.finish();
                    startActivity(intent);
                }
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    //    获取购物车列表
    public void getCartListData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_getCartList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<ShoppingCart> list = new ArrayList<ShoppingCart>();
                list = JsonUtils.parseList(obj, ShoppingCart.class);
                if (status.equals("1")) {
                    DefineUtil.NUM = 0;
                    if (list != null && list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            DefineUtil.NUM += list.get(i).getProductNum();
                            DefineUtil.NUM = Double.parseDouble(decimalFormat.format(DefineUtil.NUM));
                        }
                    }
                    Intent intent = new Intent();
                    intent.setAction("MainActivity");
                    sendBroadcast(intent);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePostCheck(DefineUtil.CARTLIST, ShoppingCartUrl.postCartListUrl(DefineUtil.USERID, DefineUtil.TOKEN), LoginActivity.this);
    }


//    private PopupWindow popupWindow;
//    private View view;
//    private ListView listView;
//
//    public void showWindow(View v) {
//        if (popupWindow == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) LoginActivity.this
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(R.layout.layout_spinner, null);
//            listView = (ListView) view.findViewById(R.id.listView);
//            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        }
//
//        // 使其聚焦
//        popupWindow.setFocusable(true);
//        // 设置允许在外点击消失
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.update();
//        // 点击back也会返回
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.showAsDropDown(v, 0, 0);
//        listView.setAdapter(adapter);
//        // 当开始时间选中后，获取选中的position，并且使结束时间的list只能从当前position开始选择
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                index = position;
//
//                if (position == 0) {
//                    accountType = "1";
//                } else {
//                    accountType = "0";
//                }
//                mEditor.putString("accountType", accountType);
//                fillData();
//                adapter.notifyDataSetChanged();
//                if (popupWindow != null) {
//                    popupWindow.dismiss();
//                }
//
//            }
//        });
//
//    }

    private String orderStatus = "1";
    private int pageNum = 1;
    private int pageSize = 100;
    private String orderId;

    //获取未支付
    //获取数据
    public void getNotPayData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_all_order", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<AllOrder> sonList = new ArrayList<AllOrder>();
                sonList = JsonUtils.parseList(obj, AllOrder.class);
                if (status.equals("1")) {
                    if (sonList != null && sonList.size() != 0) {
                        if (orderStatus.equals("1")) {
                            DefineUtil.NOTPAY_NUM = sonList.size();
                        }
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePostCheck(DefineUtil.ORDER_LIST, AllOrderUrl.postAllOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderStatus, String.valueOf(pageSize), String.valueOf(pageNum), orderId), LoginActivity.this);
    }
}
