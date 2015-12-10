package com.aglook.comapp.util;

/**
 * Created by aglook on 2015/11/11.
 */
public class DefineUtil {

    //    测试环境端口
//    public static String URL_HOST = "http://192.168.1.118:8080/payment/";
//    public static String URL_HOST = "http://192.168.1.105:8080/payment/";
//        public static String URL_HOST = "http://192.168.1.103:8080/payment/";

    /**
     * 正式环境
     */
    public static String URL_HOST = "http://trade.decxgroup.com/trade/";

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

    /**
     * 首页
     */
    //首页商品信息列表
    public static String HOT_LIST = URL_HOST + "hot-list";
    //首页商品信息详情
    public static String PRODUCT_DETAIL = URL_HOST + "product-detail";
    //首页轮播图
    public static String INDEX_SCROLLPIC = URL_HOST + "index/scrollPic";
    //收藏
    public static String COLLECT = URL_HOST + "goods/add/collect";
    //删除
    public static String DELETE_COLLECT = URL_HOST + "goods/delete/collect";
    //商品收藏列表
    public static String COLLECT_LIST = URL_HOST + "goods/collect/list";
    //首页资讯条
    public static String INFORMATION = URL_HOST + "index/information";
    //资讯列表
    public static String INFOR_LIST = URL_HOST + "information/list";

    /**
     * 商品搜索
     */
    public static String SEARCH = URL_HOST + "search";


    //    购物车
    // 加入购物车
    public static String ADDCART = URL_HOST + "shopping/addCart";
    //购物车编辑
    public static String EDIT_CART = URL_HOST + "shopping/edit/cart";
    //    购物车列表
    public static String CARTLIST = URL_HOST + "shopping/cartList";

    /* 确认订单*/
    public static String CREATE_ORDER = URL_HOST + "order/create/order";
    //订单列表
    public static String ORDER_LIST = URL_HOST + "order/order/list";
    //取消订单
    public static String CANCEL_ORDER = URL_HOST + "order/cancel/order";
    //订单详情
    public static String ORDER_DETAIL = URL_HOST + "order/details";


    /**
     * 常用联系人
     */
    //获取常用联系人
    public static String CONTACT_USER = URL_HOST + "userInfo/contact/user";
    //添加常用联系人
    public static String ADD_CONTACT = URL_HOST + "userInfo/add/contactUser";
    //删除常用联系人
    public static String DELETE_CONTACT = URL_HOST + "userInfo/delete/contactUser";


    /**
     * 司机
     */
    //司机列表
    public static String DRIVER_LIST = URL_HOST + "driver/list";
    //添加司机
    public static String DRIVER_ADD = URL_HOST + "driver/add";
    //更新司机信息
    public static String DRIVER_UPDATE = URL_HOST + "driver/update";

    /**
     * 仓单
     */
    //用户仓单列表
    public static String CANG_DAN = URL_HOST + "app/api";


    /**
     * 设置
     */
    //退出程序
    public static String LOGIN_OUT = URL_HOST + "login-out";
    //帮助中心
    public static String SETTING_HELP=URL_HOST+"index/help";

    //TOKEN
    public static String TOKEN;
    public static String USERID;

    //购物车个数
    public static int NUM = 0;

    //支付
    public static String PAY = URL_HOST + "payment/v2/order/pay";
    //verson
    public static String VERSON = "1.0";
    //    商户号
    public static String MERCHANTNO = "Q0000157";
    //协议适用范围
    public static String SERVICESCOPE = "www";
    //签名类型
    public static String SIGNTYPE = "MD5";
}
