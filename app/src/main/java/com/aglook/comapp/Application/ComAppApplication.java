package com.aglook.comapp.Application;

import android.app.Application;

import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.Message;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/11/11.
 */
public class ComAppApplication extends Application {
    private static ComAppApplication instance;
    private Login login;
    private  DbUtils db;

    public  DbUtils getDb() {
        return db;
    }

    public void setDb(DbUtils db) {
        this.db = db;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        db=DbUtils.create(this,"MESSAGE");
        Message message=null;
        List<Message>mList=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            message = new Message();
            message.setId(i);
            message.setTitle("title-__" + i);
            mList.add(message);
        }

        for (int i = 0; i < mList.size(); i++) {
            try {
                db.save(mList.get(i));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    public static ComAppApplication getInstance(){
        if (null==instance){
            instance=new ComAppApplication();
        }
        return instance;
    }
}
