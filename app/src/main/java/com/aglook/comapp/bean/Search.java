package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/12/11.
 */
public class Search {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Search{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
