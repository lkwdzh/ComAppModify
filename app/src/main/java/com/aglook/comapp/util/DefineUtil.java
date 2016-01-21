package com.aglook.comapp.util;

/**
 * Created by aglook on 2015/11/11.
 */
public class DefineUtil {


    //    测试环境端口
//    public static String URL_HOST = "http://192.168.1.118:8080/payment/";
//    public static String URL_HOST = "http://192.168.1.105:8080/payment/";
    public static String URL_HOST = "http://trade.decxgroup.com/trade/";

    /**
     * 正式环境
     */
//    public static String URL_HOST = "http://www.decxagri.com/trade/";

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
    //价格走势
    public static String TREND = URL_HOST + "trend";

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
     * 消息推送
     */
    public static String PUSH_MESSAGE = URL_HOST + "push/message";
    //查看推送消息
    public static String PUSH_ARTICLE = URL_HOST + "push/article";


    /**
     * 设置
     */
    //退出程序
    public static String LOGIN_OUT = URL_HOST + "login-out";
    //帮助中心
    public static String SETTING_HELP = URL_HOST + "index/help";

    /**
     * 注册
     */
    public static String REGISTER = URL_HOST + "userInfo/register";
    //验证码
    public static String GETPHONECODE = URL_HOST + "userInfo/getPhoneCode";


    /**
     * 地址管理
     */
    //地址列表
    public static String ADDRESS_LIST = URL_HOST + "userAddress/list";
    //地址添加
    public static String ADDRESS_ADD = URL_HOST + "userAddress/add";
    //地址修改
    public static String ADDRESS_UPDATE = URL_HOST + "userAddress/update";


    //TOKEN
    public static String TOKEN;
    public static String USERID;

    //购物车个数
    public static double NUM = 0;

    //支付
    public static String PAY = URL_HOST + "payment/v2/order/pay";
    //verson
    public static String VERSON = "1.0";
    //    商户号
    public static String MERCHANTNO = "Q0000167";
    //协议适用范围
    public static String SERVICESCOPE = "www";
    //协议适用范围
    public static String SERVICESCOPEPER = "ep";
    //签名类型
    public static String SIGNTYPE = "MD5";

    //是否绑定银行卡
    public static boolean BANKBAND;
    public static int FLAG = 0;

    /**
     * 未支付订单个数
     */
    public static int NOTPAY_NUM = 0;

    /**
     * 设备号
     */
    public static String DEVICE_NUM;

    /**
     * 判断首页是否是登录
     */
    public static boolean ISUSERID = false;

    /**
     * 检查更新
     */
    public static String CHECK_UPDATE = URL_HOST + "phone/appVersion/check";

    /**
     * 找回密码
     */
    public static String FIND_PASSWORD=URL_HOST+"userInfo/getBackPassword";
    /**
     * 找回密码验证码
     */
    public static String FIND_PWD_CODE=URL_HOST+"userInfo/getPhoneCodeForGetBackPwd";

    //程序是否启动
    public static boolean IS_LAUNCH = false;
}
