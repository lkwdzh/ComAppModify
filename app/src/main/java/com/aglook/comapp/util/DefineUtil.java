package com.aglook.comapp.util;

/**
 * Created by aglook on 2015/11/11.
 */
public class DefineUtil {

    //    测试环境端口
    public static String URL_HOST = "http://192.168.1.146:8080/";

    //    登录
    public static String LOGIN_IN = URL_HOST + "payment/login-in";

    //    修改基本信息
    public static String PERSON_UPDATE = URL_HOST + "payment/userInfo/update";
    //    修改密码
    public static String CHANGE_PWD = URL_HOST + "payment/userInfo/change-pwd";

    //    银行列表
    public static String CODE_LIST = URL_HOST + "payment/bank/code-list";
    //    银行卡列表
    public static String BANKCARD_LIST = URL_HOST + "payment/userInfo/bankCard-list";
    //    绑定银行卡
    public static String BANKCARD = URL_HOST + "payment/bank/binding/bankCard";
    //设置默认银行卡
    public static String BINDING_DEFAULT = URL_HOST + "payment/bank/binding-default";
    //删除银行卡
    public static String DELETE_BANKCARD = URL_HOST + "payment/bank/delete/bankCard";

    //商品信息分类
    public static String CATEGORY = URL_HOST + "payment/category";
    //根据分类获取想要的商品列表，筛选
    public static String CATEGORY_DETAIL = URL_HOST + "payment/category-detail";

    //首页商品信息列表
    public static String HOT_LIST = URL_HOST + "payment/hot-list";

    //首页商品信息详情
    public static String PRODUCT_DETAIL = URL_HOST + "payment/product-detail";


    //TOKEN
    public static String TOKEN;
    public static String USERID;
}
