package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/10.
 */
public class DriverList implements Serializable{
    private boolean isChecked;
    private String  weitht;
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeitht() {
        return weitht;
    }

    public void setWeitht(String weitht) {
        this.weitht = weitht;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "DriverList{" +
                "isChecked=" + isChecked +
                ", weitht='" + weitht + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
