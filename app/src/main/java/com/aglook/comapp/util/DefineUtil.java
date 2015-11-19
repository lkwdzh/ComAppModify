package com.aglook.comapp.util;

/**
 * Created by aglook on 2015/11/11.
 */
public class DefineUtil {

    //    测试环境端口
    public static String URL_HOST = "http://192.168.1.118:8080/payment/";

    //    登录
    public static String LOGIN_IN = URL_HOST + "login-in";

    //    修改基本信息
    public static String PERSON_UPDATE = URL_HOST + "userInfo/update";
    //    修改密码
    public static String CHANGE_PWD = URL_HOST + "userInfo/change-pwd";

    //    银行列表
    public static String CODE_LIST = URL_HOST + "bank/code-list";
    //    银行卡列表
    public static String BANKCARD_LIST = URL_HOST + "userInfo/bankCard-list";
    //    绑定银行卡
    public static String BANKCARD = URL_HOST + "bank/binding/bankCard";
    //设置默认银行卡
    public static String BINDING_DEFAULT = URL_HOST + "bank/binding-default";
    //删除银行卡
    public static String DELETE_BANKCARD = URL_HOST + "bank/delete/bankCard";

    //商品信息分类
    public static String CATEGORY = URL_HOST + "category";
    //根据分类获取想要的商品列表，筛选
    public static String CATEGORY_DETAIL = URL_HOST + "category-detail";

    //首页商品信息列表
    public static String HOT_LIST = URL_HOST + "hot-list";

    //首页商品信息详情
    public static String PRODUCT_DETAIL = URL_HOST + "product-detail";



//    购物车
   // 加入购物车
    public static String ADDCART=URL_HOST+"shopping/addCart";
    //购物车编辑
    public static String EDIT_CART=URL_HOST+"shopping/edit/cart";
//    购物车列表
    public static String CARTLIST=URL_HOST+"shopping/cartList";

/* 确认订单*/
    public static String CREATE_ORDER=URL_HOST+"order/create/order";
    //订单列表
    public static String ORDER_LIST=URL_HOST+"order/order/list";
    //取消订单
    public static String CANCEL_ORDER=URL_HOST+"order/cancel/order";

    //TOKEN
    public static String TOKEN;
    public static String USERID;
}
