package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AddressDialogShengAdapter;
import com.aglook.comapp.adapter.AddressDialogShiAdapter;
import com.aglook.comapp.bean.Address;
import com.aglook.comapp.bean.CharacterParser;
import com.aglook.comapp.bean.PinyinComparator;
import com.aglook.comapp.bean.Sheng;
import com.aglook.comapp.bean.ShengComparaotr;
import com.aglook.comapp.bean.ShiComparator;
import com.aglook.comapp.bean.SortModel;
import com.aglook.comapp.url.AddressUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddAddressActivity extends BaseActivity {


    private EditText et_detail;
    private CheckBox cb_moren;
    private Button btn_baocun;
    private String diqu;
    private String name;
    private String phone;

    private String defaultFlag;
    private String detail;
    private String sheng,shi;
    private int selectPosition = -1;//选择的省
    private List<SortModel>mShiList=new ArrayList<>();
    private AddressDialogShiAdapter shiAdapter;
    private TextView tv_diqu;
    private EditText et_name_add_address;
    private EditText et_phone_add_address;
    private CustomProgress customProgress;

    private boolean isOnly;//判断是否是第一个

    private RelativeLayout rl_check;

    private View view_check;

    private boolean isModify;//是否是修改
    private TextView right_text;

       private Address modifyAddress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_add_address);
        isModify=getIntent().getBooleanExtra("isModify",false);
        modifyAddress= (Address) getIntent().getSerializableExtra("modifyAddress");
        if (isModify){
            setTitleBar("修改收货地址");
        }else {
            setTitleBar("新建收货地址");
        }
        ExitApplication.getInstance().addActivity(this);
        isOnly=getIntent().getBooleanExtra("isOnly",false);
        init();
        click();
        getData();
    }

    public void init() {
        characterParser = CharacterParser.getInstance();
        tv_diqu = (TextView) findViewById(R.id.tv_diqu);
        et_name_add_address = (EditText) findViewById(R.id.et_name_add_address);
        et_phone_add_address = (EditText) findViewById(R.id.et_phone_add_address);
        et_detail = (EditText) findViewById(R.id.et_detail);
        cb_moren = (CheckBox) findViewById(R.id.cb_moren);
        btn_baocun = (Button) findViewById(R.id.btn_baocun);
        shiAdapter = new AddressDialogShiAdapter(mShiList,AddAddressActivity.this);
        rl_check = (RelativeLayout) findViewById(R.id.rl_check);
        view_check = (View) findViewById(R.id.view_check);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("删除");
        if (isModify){
            if (modifyAddress.isCheck()){
                right_text.setVisibility(View.GONE);
            }else {
                right_text.setVisibility(View.VISIBLE);
            }
        }else {
            right_text.setVisibility(View.GONE);
        }
        //假如是,则默认是默认
        if (isOnly){
            rl_check.setVisibility(View.GONE);
            view_check.setVisibility(View.GONE);
        }else {
            rl_check.setVisibility(View.VISIBLE);
            view_check.setVisibility(View.VISIBLE);
        }

        //假如是修改，则填充
        if (isModify&&modifyAddress!=null){
            et_name_add_address.setText(modifyAddress.getUserName());
            et_phone_add_address.setText(modifyAddress.getUserPhone());
            tv_diqu.setText(modifyAddress.getUserArea());
            et_detail.setText(modifyAddress.getUserAddress());
                if (modifyAddress.getDefaultFlag()!=null){
                    if (modifyAddress.getDefaultFlag().equals("1")){
                        //默认
                        rl_check.setVisibility(View.GONE);
                        view_check.setVisibility(View.GONE);
                        defaultFlag="1";
                    }else {
                        rl_check.setVisibility(View.VISIBLE);
                        view_check.setVisibility(View.VISIBLE);
                    }
                }
        }

    }

    public void click() {
        tv_diqu.setOnClickListener(this);
        cb_moren.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    defaultFlag="1";//默认
                }else {
                    defaultFlag="0";
                }
            }
        });
        btn_baocun.setOnClickListener(this);
        right_text.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.tv_diqu:
                showDialogSheng();
                break;
            case R.id.btn_baocun:
                getInput();
                break;
            case R.id.right_text:
                deleteAddress();
                break;
        }
    }

    //获取输入信息
    public void getInput(){
        name=AppUtils.toStringTrim_ET(et_name_add_address);
        phone=AppUtils.toStringTrim_ET(et_phone_add_address);
        diqu=AppUtils.toStringTrim_TV(tv_diqu);
        detail=AppUtils.toStringTrim_ET(et_detail);
        if (name==null||"".equals(name)){
            AppUtils.toastText(AddAddressActivity.this,"请输入收货人姓名");
            return;
        }
        if (phone==null||"".equals(phone)){
            AppUtils.toastText(AddAddressActivity.this,"请输入收货人手机号");
            return;
        }
        if (diqu==null||"".equals(diqu)){
            AppUtils.toastText(AddAddressActivity.this,"请选择地区");
            return;
        }
        if (detail==null||"".equals(detail)){
            AppUtils.toastText(AddAddressActivity.this,"请输入详细地址");
            return;
        }
        if (isModify){
            modifyData();
        }else {
            //提交
            upData();
        }
    }

    //提交
    public void upData(){
        customProgress=CustomProgress.show(AddAddressActivity.this,"",true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_addressAdd", arg0.result);
                String status= JsonUtils.getJsonParam(arg0.result,"status");
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                if (status.equals("1")){
                    AddAddressActivity.this.setResult(RESULT_OK);
                    AddAddressActivity.this.finish();
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.ADDRESS_ADD, AddressUrl.postAddAddressUrl(DefineUtil.USERID,DefineUtil.TOKEN,defaultFlag,diqu,detail,phone,name),AddAddressActivity.this);
    }


    //修改
    public void modifyData(){
        customProgress=CustomProgress.show(AddAddressActivity.this,"",true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_addressAdd", arg0.result);
                String status= JsonUtils.getJsonParam(arg0.result,"status");
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                if (status.equals("1")){
                    AddAddressActivity.this.setResult(RESULT_OK);
                    AddAddressActivity.this.finish();
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.ADDRESS_UPDATE, AddressUrl.postUpdateAddressUlr(DefineUtil.USERID, DefineUtil.TOKEN, String.valueOf(modifyAddress.getId()), diqu, detail, phone, name, defaultFlag),AddAddressActivity.this);
    }


    //删除
    public void deleteAddress() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_addressdelete", arg0.result);
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    AddAddressActivity.this.setResult(RESULT_OK);
                    AddAddressActivity.this.finish();
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.ADDRESS_UPDATE, AddressUrl.postDeletaAddressUrl(DefineUtil.USERID, DefineUtil.TOKEN, String.valueOf(modifyAddress.getId())), AddAddressActivity.this);
    }

    private Dialog dialog;
    private ListView lv_dialog_lv;

    public void showDialogSheng() {
        LayoutInflater inflater = LayoutInflater.from(AddAddressActivity.this);
        View inView = inflater.inflate(R.layout.layout_dialog_lv, null);
        lv_dialog_lv=(ListView)inView.findViewById(R.id.lv_dialog_lv);
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAddressActivity.this);
        builder.create();
        builder.setView(inView);
//        builder.setCancelable(false);
        dialog = builder.show();
        AddressDialogShengAdapter shengAdapter = new AddressDialogShengAdapter(mShengList, AddAddressActivity.this);
        lv_dialog_lv.setAdapter(shengAdapter);

        lv_dialog_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sheng=mShengList.get(position).getP().replaceAll(" ","");
//                tv_sheng.setText(sheng);
                selectPosition=position;
                dialog.dismiss();
                mShiList.clear();
                mShiList.addAll(mShengList.get(position).getC());
                shiAdapter.notifyDataSetChanged();
//                Log.d("result_sort", mShiList.toString());
                showDialogShi();
            }
        });
    }

    public void showDialogShi() {
        LayoutInflater inflater = LayoutInflater.from(AddAddressActivity.this);
        View inView = inflater.inflate(R.layout.layout_dialog_lv, null);
        lv_dialog_lv=(ListView)inView.findViewById(R.id.lv_dialog_lv);
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAddressActivity.this);
        builder.create();
        builder.setView(inView);
//        builder.setCancelable(false);
        dialog = builder.show();

        lv_dialog_lv.setAdapter(shiAdapter);
        lv_dialog_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    shi = mShiList.get(position).getN().replaceAll(" ", "");
//                    tv_shi.setText(shi);
                    dialog.dismiss();
                    tv_diqu.setText(sheng+shi);
            }
        });

    }

    private String orderId;
    private String money;
    private String totalFee;



    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    private List<Sheng> mShengList;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    private ShengComparaotr shengComparaotr;


    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private ShiComparator shiComparator = new ShiComparator();

    //获取城市数据
    public void getData() {
//实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        shengComparaotr = new ShengComparaotr();

        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = this.getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
            String citylist = JsonUtils.getJsonParam(sb.toString(), "citylist");
            mShengList = JsonUtils.parseList(citylist, Sheng.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] s = new String[mShengList.size()];
        for (int i = 0; i < mShengList.size(); i++) {
            String n = mShengList.get(i).getP();
            s[i] = n;
        }
        mShengList = filleSData(s);
// 根据a-z进行排序源数据
        Collections.sort(mShengList, shengComparaotr);
        String[] ss;
        for (int i = 0; i < mShengList.size(); i++) {
            ss = new String[mShengList.get(i).getC().size()];
            for (int j = 0; j < mShengList.get(i).getC().size(); j++) {
                ss[j] = mShengList.get(i).getC().get(j).getN();
            }
            SourceDateList=new ArrayList<>();
            SourceDateList = filledData(ss);
            Collections.sort(SourceDateList, pinyinComparator);
            mShengList.get(i).setC(SourceDateList);
//            Log.d("result_sort", mShengList.get(i).toString());
        }




    }


    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setN(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }


    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<Sheng> filleSData(String[] date) {
        List<Sheng> mSortList = new ArrayList<Sheng>();

        for (int i = 0; i < date.length; i++) {
            Sheng sheng = new Sheng();
            sheng.setP(date[i]);
            sheng.setC(mShengList.get(i).getC());
//            Log.d("result__",mShengList.get(i).getC().toString());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sheng.setSortLetters(sortString.toUpperCase());
            } else {
                sheng.setSortLetters("#");
            }

            mSortList.add(sheng);
        }
        return mSortList;

    }
}
