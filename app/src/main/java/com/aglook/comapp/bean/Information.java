package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/30.
 */
public class Information {
    private String classId;
    private String className;
    private String classType;
    private String classOrder;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(String classOrder) {
        this.classOrder = classOrder;
    }

    @Override
    public String toString() {
        return "Information{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", classType='" + classType + '\'' +
                ", classOrder='" + classOrder + '\'' +
                '}';
    }
}
