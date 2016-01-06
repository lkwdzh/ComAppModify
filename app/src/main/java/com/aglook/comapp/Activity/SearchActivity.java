package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.SearchGVAdapter;
import com.aglook.comapp.adapter.SearchLVAdapter;
import com.aglook.comapp.bean.Search;
import com.aglook.comapp.util.AppUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

public class
        SearchActivity extends BaseActivity {


    private EditText et_search;
    private ImageView iv_search;
    private ListView lv_search;
    private GridView gv_search;

    private List<String> gvList = new ArrayList<>();
    private List<String> lvList = new ArrayList<>();
    private SearchGVAdapter gvAdapter;
    private SearchLVAdapter lvAdapter;
    private String productName;

    private DbUtils db;
    private Search search;
    private List<Search> sonList = new ArrayList<>();

    private final int SEARCH = 0;
    private boolean isSearch;
    private TextView tv_search;
    private Button btn_search;
    private ImageView iv_left;


    @Override
    public void initView() {
        setContentView(R.layout.activity_search);
        ExitApplication.getInstance().addActivity(this);
        init();
        getData();
        click();
    }

    public void init() {
        et_search = (EditText) findViewById(R.id.et_search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        lv_search = (ListView) findViewById(R.id.lv_search);
        gv_search = (GridView) findViewById(R.id.gv_search);
        tv_search = (TextView) findViewById(R.id.tv_search);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        db = DbUtils.create(this, "SEARCH");
        //写死热搜
        gvList.add("热搜");
        gvList.add("芝麻");
        gvList.add("大豆");
        gvList.add("花生");
        gvList.add("玉米");
        gvList.add("小麦");
        gvList.add("索马里");
        gvList.add("苏丹");
        gvList.add("乌干达");
        gvList.add("缅甸");
        gvList.add("越南");
        gvList.add("中国");
//        for (int i = 0; i < 10; i++) {
//            gvList.add("数据" + i);
//            lvList.add("数据" + i);
//        }
        gvAdapter = new SearchGVAdapter(gvList, SearchActivity.this);
        gv_search.setAdapter(gvAdapter);
        View view= LayoutInflater.from(this).inflate(R.layout.search_footview,null);
        btn_search = (Button) view.findViewById(R.id.btn_search);
        lv_search.addFooterView(view);
        lvAdapter = new SearchLVAdapter(lvList, SearchActivity.this);
        lv_search.setAdapter(lvAdapter);

    }


    //获取数据
    public void getData() {
        //从数据库取数据
        sonList = new ArrayList<>();
        List<String> sonStr = new ArrayList<>();
        if (isSearch = true) {
            isSearch = false;
            sonList.clear();
            lvList.clear();
        }
        try {
            sonList = db.findAll(Selector.from(Search.class).orderBy("id", true).limit(10));
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (sonList != null && sonList.size() != 0) {

            for (int i = 0; i < sonList.size(); i++) {
                sonStr.add(sonList.get(i).getContent());
            }
            lvList.addAll(sonStr);
            lvAdapter.notifyDataSetChanged();
        }
        if (sonList==null||sonList.size()==0){
            btn_search.setVisibility(View.GONE);
        }else {
            btn_search.setVisibility(View.VISIBLE);
        }
    }

    public void click() {
        btn_search.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        gv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    et_search.setText(gvList.get(position));
                    saveContent();
                    save();
                    Intent intent = new Intent(SearchActivity.this, ScreenActivity.class);
                    intent.putExtra("productName", productName);
                    intent.putExtra("isSearch", true);
                    startActivityForResult(intent, SEARCH);
                }
            }
        });

        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_search.setText(lvList.get(position));
                saveContent();
                save();
                Intent intent = new Intent(SearchActivity.this, ScreenActivity.class);
                intent.putExtra("productName", productName);
                intent.putExtra("isSearch", true);
                startActivityForResult(intent, SEARCH);
            }
        });

    }

    private boolean isEquals;
    private int searchId;

    //将输入的内容存到数据库中
    public void saveContent() {
        productName = AppUtils.toStringTrim_ET(et_search);

        //判断是否与sonlist中重复
        if (sonList != null && sonList.size() != 0) {

            for (int i = 0; i < sonList.size(); i++) {
                if (productName.equals(sonList.get(i).getContent())) {
                    searchId = sonList.get(i).getId();
                    isEquals = true;
//                    Log.d("result___isEquals", isEquals + "##############");
                    return;
                } else {
//                    Log.d("result___isEquals", isEquals + "$$$$$$$$$$$$$$$");
                    isEquals = false;
//                    Log.d("result_equal", productName + "_____" + sonList.get(i).getContent());
                }


            }
        }

    }

    public void save() {
        if (!isEquals) {
//            Log.d("result___isEquals", isEquals + "@@@@@@");
            try {
                search = new Search();
                search.setContent(productName);
                db.save(search);
            } catch (DbException e) {
                e.printStackTrace();
            }
        } else {
//            Log.d("result___isEquals", isEquals + "!!!!!!!!");
            try {
                db.deleteById(Search.class, searchId);
                search = new Search();
                search.setContent(productName);
                db.save(search);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SEARCH && resultCode == RESULT_OK) {
            isSearch = true;
            getData();
        }
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
//                productName = AppUtils.toStringTrim_ET(et_search);
                saveContent();
                save();
                Intent intent = new Intent(SearchActivity.this, ScreenActivity.class);
                intent.putExtra("productName", productName);
                intent.putExtra("isSearch", true);
                startActivityForResult(intent, SEARCH);
                break;
            case R.id.btn_search:
                try {
                    db.deleteAll(Search.class);
                    sonList.clear();
                    lvList.clear();
                    btn_search.setVisibility(View.GONE);
                    lvAdapter.notifyDataSetChanged();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_left:
                SearchActivity.this.finish();
                break;
        }
    }


}
