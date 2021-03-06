package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.BandCardDialogAdapter;
import com.aglook.comapp.bean.BandCardList;
import com.aglook.comapp.url.CardListUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class BandCardActivity extends BaseActivity {


    private TextView right_text;
    private EditText et_name_bang_card;
    private EditText et_num_bang_card;
    private TextView tv_bank_bang_card;
    private EditText et_phone_bang_card;
    private ListView lv_dialog_lv;

    private String userName, cardNo, bankCode, bankAlis, cardPhone;
    private String cardType = "0";//0:储蓄卡1:信用卡
    private BandCardDialogAdapter adapter;

    private CustomProgress customProgress;
    private TextView tv_name_bang_card;
    private TextView tv_name_remind;
    private ComAppApplication comAppApplication;
    private LinearLayout ll_zhiHang;
    private EditText et_zhiHang_bang_card;

    private boolean isFirst;
    private LinearLayout ll_moren;
    private CheckBox cb_moren;

    private String bankCompanyHan;//  开户支行
    private String bankDefault ;//（1 默认 0 非默认）

    @Override
    public void initView() {
        setContentView(R.layout.activity_band_card);
        setTitleBar("绑定银行账户");
//        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        init();
        click();
    }

    public void init() {
        isFirst = getIntent().getBooleanExtra("isFirst", false);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        right_text.setVisibility(View.VISIBLE);
        et_name_bang_card = (EditText) findViewById(R.id.et_name_bang_card);
        et_num_bang_card = (EditText) findViewById(R.id.et_num_bang_card);
        tv_bank_bang_card = (TextView) findViewById(R.id.tv_bank_bang_card);
        et_phone_bang_card = (EditText) findViewById(R.id.et_phone_bang_card);
        tv_name_bang_card = (TextView) findViewById(R.id.tv_name_bang_card);
        tv_name_remind = (TextView) findViewById(R.id.tv_name_remind);
        ll_zhiHang = (LinearLayout) findViewById(R.id.ll_zhiHang);
        et_zhiHang_bang_card = (EditText) findViewById(R.id.et_zhiHang_bang_card);
        if (comAppApplication.getLogin().getPshUser().getUserType() == 2) {
            //个人
            tv_name_bang_card.setText("持卡人姓名");
            et_name_bang_card.setHint("请输入持卡人姓名");
            tv_name_remind.setText("姓名要与用户真实姓名一致否则绑定不成功");
            ll_zhiHang.setVisibility(View.GONE);
        } else {
            //公司
            tv_name_bang_card.setText("账户公司名称");
            et_name_bang_card.setHint("请输入账户公司名称");
            tv_name_remind.setText("账户公司名称要与用户公司名称一致否则绑定不成功");
            ll_zhiHang.setVisibility(View.VISIBLE);
        }
        ll_moren = (LinearLayout) findViewById(R.id.ll_moren);
        cb_moren = (CheckBox) findViewById(R.id.cb_moren);
        if (isFirst) {
            ll_moren.setVisibility(View.GONE);
            cb_moren.setChecked(true);
            bankDefault="1";
        }
    }

    public void click() {
        tv_bank_bang_card.setOnClickListener(this);
        right_text.setOnClickListener(this);
        cb_moren.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    bankDefault="1";
                }else {
                    bankDefault="0";
                }
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bank_bang_card:
                showDialog();
                break;
            case R.id.right_text:
                getInput();

                break;
        }
    }

    public void getInput() {
        userName = AppUtils.toStringTrim_ET(et_name_bang_card);
        cardNo = AppUtils.toStringTrim_ET(et_num_bang_card);
        bankCompanyHan = AppUtils.toStringTrim_ET(et_zhiHang_bang_card);
        if (userName == null || "".equals(userName)) {
            AppUtils.toastText(BandCardActivity.this, "姓名不能为空");
            return;
        }
        if (cardNo == null || "".equals(cardNo)) {
            AppUtils.toastText(BandCardActivity.this, "银行卡号不能为空");
            return;
        }
        if (bankCode == null || "".equals(bankCode)) {
            AppUtils.toastText(BandCardActivity.this, "请选择银行");
            return;
        }
        if (comAppApplication.getLogin().getPshUser().getUserType() == 1) {
            if (bankCompanyHan == null || "".equals(bankCompanyHan)) {
            AppUtils.toastText(BandCardActivity.this, "开户支行不能为空");
            return;
        }
    }
        bandCard();
    }


    private Dialog dialog;
    private List<BandCardList> mList;

    public void showDialog() {
        LayoutInflater inflater = (LayoutInflater) BandCardActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_dialog_lv, null);
        lv_dialog_lv = (ListView) view.findViewById(R.id.lv_dialog_lv);
        AlertDialog.Builder builder = new AlertDialog.Builder(BandCardActivity.this);
        builder.create();
        builder.setView(view);
        dialog = builder.show();
        dialog.setTitle("请选择银行");
        mList = new ArrayList<>();
        getDialogData();
        lv_dialog_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bankAlis = mList.get(position).getBankCodeName();
                bankCode = mList.get(position).getBankCodeId();
                dialog.dismiss();
                tv_bank_bang_card.setText(bankAlis);
            }
        });
    }


    //获取银行卡列表
    public void getDialogData() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_bandCard", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                mList = JsonUtils.parseList(obj, BandCardList.class);
                if (status.equals("1")) {
                    adapter = new BandCardDialogAdapter(BandCardActivity.this, mList);
                    lv_dialog_lv.setAdapter(adapter);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.CODE_LIST, BandCardActivity.this);
    }

    //绑定银行卡
    public void bandCard() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
                Log.d("result_bandCard_res", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {

                    BandCardActivity.this.setResult(1);
                    finish();
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePostUp(DefineUtil.BANKCARD, CardListUrl.postBandUrl(DefineUtil.USERID, DefineUtil.TOKEN, cardNo, userName, cardType, bankCode, bankAlis, cardPhone,bankCompanyHan,bankDefault), BandCardActivity.this);
    }
}
