package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/11/30.
 */
public class HomePage {
    private String categoryName;
    private List<HomePageList>list;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<HomePageList> getList() {
        return list;
    }

    public void setList(List<HomePageList> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HomePage{" +
                "categoryName='" + categoryName + '\'' +
                ", list=" + list +
                '}';
    }
}
