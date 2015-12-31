package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
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
    @Override
    public void initView() {
        setContentView(R.layout.activity_band_card);
        setTitleBar("绑定银行卡");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {

        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        right_text.setVisibility(View.VISIBLE);
        et_name_bang_card = (EditText) findViewById(R.id.et_name_bang_card);
        et_num_bang_card = (EditText) findViewById(R.id.et_num_bang_card);
        tv_bank_bang_card = (TextView) findViewById(R.id.tv_bank_bang_card);
        et_phone_bang_card = (EditText) findViewById(R.id.et_phone_bang_card);
    }

    public void click() {
        tv_bank_bang_card.setOnClickListener(this);
        right_text.setOnClickListener(this);
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
        cardPhone = AppUtils.toStringTrim_ET(et_phone_bang_card);
        if (userName==null||"".equals(userName)){
            AppUtils.toastText(BandCardActivity.this,"姓名不能为空");
            return;
        }
        if (cardNo==null||"".equals(cardNo)){
            AppUtils.toastText(BandCardActivity.this,"银行卡号不能为空");
            return;
        }
        if (bankCode==null||"".equals(bankCode)){
            AppUtils.toastText(BandCardActivity.this,"请选择银行");
            return;
        }
        if (cardPhone==null||"".equals(cardPhone)){
            AppUtils.toastText(BandCardActivity.this,"银行预留手机号不能为空");
            return;
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
                    customProgress.dismiss();
                }
                Log.d("result_bandCard", arg0.result);
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
                    customProgress.dismiss();
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
                    customProgress.dismiss();
                }
                Log.d("result_bandCard_res", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")){
                    //如果FLAG=1，表示是从确认订单调过来的，返回时需要调到订单界面，
//                    if (DefineUtil.FLAG == 1) {
//                        Intent intent = new Intent();
//                        intent.setClass(BandCardActivity.this, MainActivity.class);
//                        DefineUtil.FLAG=2;
//                        startActivity(intent);
//                        BandCardActivity.this.finish();
//                    }else {

                        BandCardActivity.this.setResult(1);
                        finish();
//                    }
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePostUp(DefineUtil.BANKCARD, CardListUrl.postBandUrl(DefineUtil.USERID, DefineUtil.TOKEN, cardNo, userName, cardType, bankCode, bankAlis, cardPhone), BandCardActivity.this);
    }
}
