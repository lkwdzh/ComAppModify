package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/11/28.
 */
public class PickUp {
    private int pageNo;//页码
    private int pageSize;//每页显示条数
    private int iTotalRecords;//总计数
    private boolean last;//是否是最后一页
    private boolean first;//是否是第一页
    private List<PickUpList>list;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
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

    public List<PickUpList> getList() {
        return list;
    }

    public void setList(List<PickUpList> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PickUp{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", iTotalRecords=" + iTotalRecords +
                ", last=" + last +
                ", first=" + first +
                ", list=" + list +
                '}';
    }
}
