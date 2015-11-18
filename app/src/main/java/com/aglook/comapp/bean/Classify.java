package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/11/16.
 */
public class Classify {

    private String name;
    private int id;
    private List<ClassifyGV> content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassifyGV> getContent() {
        return content;
    }

    public void setContent(List<ClassifyGV> content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", content=" + content +
                '}';
    }
}
