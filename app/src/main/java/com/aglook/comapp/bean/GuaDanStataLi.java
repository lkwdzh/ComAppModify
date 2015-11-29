package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/11/29.
 */
public class GuaDanStataLi {
    private String pageNo;
    private String iTotalRecords;
    private String pageSize;
    private boolean last;
    private boolean first;
    private List<GuaDanStataLiL>list;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(String iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public List<GuaDanStataLiL> getList() {
        return list;
    }

    public void setList(List<GuaDanStataLiL> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GuaDanStataLi{" +
                "pageNo='" + pageNo + '\'' +
                ", iTotalRecords='" + iTotalRecords + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", last=" + last +
                ", first=" + first +
                ", list=" + list +
                '}';
    }
}
