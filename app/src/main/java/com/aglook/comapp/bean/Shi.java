package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/12/22.
 */
public class Shi {
    private String n;
    private String nLetters;  //显示数据拼音的首字母

    public String getnLetters() {
        return nLetters;
    }

    public void setnLetters(String nLetters) {
        this.nLetters = nLetters;
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
