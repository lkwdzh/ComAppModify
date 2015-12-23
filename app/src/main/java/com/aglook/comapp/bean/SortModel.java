package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/12/22.
 */
public class SortModel {
    private String n;
    private String sortLetters;  //显示数据拼音的首字母

    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Shi{" +
                "n='" + n + '\'' +
                '}';
    }
}
