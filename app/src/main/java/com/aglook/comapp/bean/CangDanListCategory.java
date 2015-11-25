package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/24.
 */
public class CangDanListCategory {
    private int categoryId;//id
    private String  categoryPid;//种类父类id
    private String categoryName;//种类名称
    private String categoryTitle;//种类标题
    private String  categoryKeys;//

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
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

    @Override
    public String toString() {
        return "CangDanListCategory{" +
                "categoryId=" + categoryId +
                ", categoryPid='" + categoryPid + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", categoryKeys='" + categoryKeys + '\'' +
                '}';
    }
}
