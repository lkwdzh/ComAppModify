package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AddressDialogShengAdapter;
import com.aglook.comapp.adapter.AddressDialogShiAdapter;
import com.aglook.comapp.bean.CharacterParser;
import com.aglook.comapp.bean.PinyinComparator;
import com.aglook.comapp.bean.Sheng;
import com.aglook.comapp.bean.ShengComparaotr;
import com.aglook.comapp.bean.ShiComparator;
import com.aglook.comapp.bean.SortModel;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.JsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddAddressActivity extends BaseActivity {


    private TextView tv_sheng;
    private TextView tv_shi;
    private EditText et_detail;
    private CheckBox cb_moren;
    private Button btn_baocun;
    private String sheng;
    private String shi;
    private String detail;
    private int selectPosition = -1;//选择的省
    private List<SortModel>mShiList=new ArrayList<>();
    private AddressDialogShiAdapter shiAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_add_address);
        setTitleBar("新增地址");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        characterParser = CharacterParser.getInstance();
        tv_sheng = (TextView) findViewById(R.id.tv_sheng);
        tv_shi = (TextView) findViewById(R.id.tv_shi);
        et_detail = (EditText) findViewById(R.id.et_detail);
        cb_moren = (CheckBox) findViewById(R.id.cb_moren);
        btn_baocun = (Button) findViewById(R.id.btn_baocun);
        shiAdapter = new AddressDialogShiAdapter(mShiList,AddAddressActivity.this);
    }

    public void click() {
        tv_sheng.setOnClickListener(this);
        tv_shi.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.tv_sheng:
                showDialogSheng();
                break;
            case R.id.tv_shi:
                if (selectPosition!=-1) {
                showDialogShi();
                }else {
                    AppUtils.toastText(AddAddressActivity.this, "请先选择省");
                }
                break;
        }
    }

    //获取输入信息
    public void getInput(){
        detail=AppUtils.toStringTrim_ET(et_detail);
        if (sheng==null||"".equals(sheng)){
            AppUtils.toastText(AddAddressActivity.this,"请选择所在省");
            return;
        }
        if (shi==null||"".equals(shi)){
            AppUtils.toastText(AddAddressActivity.this,"请选择所在市");
            return;
        }
        if (detail==null||"".equals(detail)){
            AppUtils.toastText(AddAddressActivity.this,"请输入详细地址");
            return;
        }

        //提交
    }

    //提交
    public void upData(){

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
                tv_sheng.setText(sheng);
                selectPosition=position;
                dialog.dismiss();
                mShiList.clear();
                mShiList.addAll(mShengList.get(position).getC());
                shiAdapter.notifyDataSetChanged();
                Log.d("result_sort", mShiList.toString());
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
                    tv_shi.setText(shi);
                    dialog.dismiss();

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
            Log.d("result_sort", mShengList.get(i).toString());
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
