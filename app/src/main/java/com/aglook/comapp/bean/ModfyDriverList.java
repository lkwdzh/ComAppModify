package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/30.
 */
public class ModfyDriverList {
    private String  id;//提货记录id
    private String getlistId;//提货单id
    private String name;
    private String tel;
    private String code;
    private String carCode;
    private String  getWeight;
    private String getAble;//0 否 1 是,2 取消

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGetlistId() {
        return getlistId;
    }

    public void setGetlistId(String getlistId) {
        this.getlistId = getlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getGetWeight() {
        return getWeight;
    }

    public void setGetWeight(String getWeight) {
        this.getWeight = getWeight;
    }

    public String getGetAble() {
        return getAble;
    }

    public void setGetAble(String getAble) {
        this.getAble = getAble;
    }

    @Override
    public String toString() {
        return "ModfyDriverList{" +
                "id='" + id + '\'' +
                ", getlistId='" + getlistId + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", code='" + code + '\'' +
                ", carCode='" + carCode + '\'' +
                ", getWeight='" + getWeight + '\'' +
                ", getAble='" + getAble + '\'' +
                '}';
    }
}
