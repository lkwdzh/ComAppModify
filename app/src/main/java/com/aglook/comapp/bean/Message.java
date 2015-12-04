package com.aglook.comapp.bean;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;

/**
 * Created by aglook on 2015/12/3.
 */
public class Message {
    @NoAutoIncrement
    private int id;

    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
