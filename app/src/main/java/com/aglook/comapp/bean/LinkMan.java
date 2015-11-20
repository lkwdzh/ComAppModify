package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/20.
 */
public class LinkMan implements Serializable{
    private int id;
    private boolean isChecked;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "id=" + id +
                ", isChecked=" + isChecked +
                ", name='" + name + '\'' +
                '}';
    }
}
