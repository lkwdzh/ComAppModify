package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/12/22.
 */
public class Sheng {
    private String p;
    private String sortLetters;  //显示数据拼音的首字母

    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    private List<SortModel> c;

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public List<SortModel> getC() {
        return c;
    }

    public void setC(List<SortModel> c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Sheng{" +
                "p='" + p + '\'' +
                ", c=" + c +
                '}';
    }
}
