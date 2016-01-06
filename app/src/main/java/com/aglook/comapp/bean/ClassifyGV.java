package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/16.
 */
public class ClassifyGV {
    private String categoryId;//商品id
    private String categoryPid;//
    private String categoryName;//商品名称
    private String categoryTitle;
    private String categoryKeys;
    private String categoryDesc;
    private String categoryOrder;
    private String categoryNationalFlag;//国旗

    public String getCategoryNationalFlag() {
        return categoryNationalFlag;
    }

    public void setCategoryNationalFlag(String categoryNationalFlag) {
        this.categoryNationalFlag = categoryNationalFlag;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(String categoryPid) {
        this.categoryPid = categoryPid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryKeys() {
        return categoryKeys;
    }

    public void setCategoryKeys(String categoryKeys) {
        this.categoryKeys = categoryKeys;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(String categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    @Override
    public String toString() {
        return "ClassifyGV{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryPid='" + categoryPid + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", categoryKeys='" + categoryKeys + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                ", categoryOrder='" + categoryOrder + '\'' +
                ", categoryNationalFlag='" + categoryNationalFlag + '\'' +
                '}';
    }
}
