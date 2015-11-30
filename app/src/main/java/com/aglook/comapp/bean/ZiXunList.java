package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/30.
 */
public class ZiXunList {
    private String articleId;
    private String articleName;
    private String articleAtime;
    private String articleClicknum;
    private String classId;
    private String url;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleAtime() {
        return articleAtime;
    }

    public void setArticleAtime(String articleAtime) {
        this.articleAtime = articleAtime;
    }

    public String getArticleClicknum() {
        return articleClicknum;
    }

    public void setArticleClicknum(String articleClicknum) {
        this.articleClicknum = articleClicknum;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ZiXunList{" +
                "articleId='" + articleId + '\'' +
                ", articleName='" + articleName + '\'' +
                ", articleAtime='" + articleAtime + '\'' +
                ", articleClicknum='" + articleClicknum + '\'' +
                ", classId='" + classId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
