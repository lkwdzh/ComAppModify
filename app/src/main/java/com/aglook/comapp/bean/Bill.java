package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2016/1/22.
 */
public class Bill implements Serializable{
    private String taitou;
    private String conpanyName;
    private String numBill;
    private String companyAddress;
    private String phone;
    private String bank;
    private String bankNum;
    private String content;

    public String getTaitou() {
        return taitou;
    }

    public void setTaitou(String taitou) {
        this.taitou = taitou;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConpanyName() {
        return conpanyName;
    }

    public void setConpanyName(String conpanyName) {
        this.conpanyName = conpanyName;
    }

    public String getNumBill() {
        return numBill;
    }

    public void setNumBill(String numBill) {
        this.numBill = numBill;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "taitou='" + taitou + '\'' +
                ", conpanyName='" + conpanyName + '\'' +
                ", numBill='" + numBill + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", bank='" + bank + '\'' +
                ", bankNum='" + bankNum + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
