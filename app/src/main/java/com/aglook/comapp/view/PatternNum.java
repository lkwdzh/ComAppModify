package com.aglook.comapp.view;


import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aglook on 2016/1/7.
 * 匹配电话号与车牌号
 */
public class PatternNum {
    //电话号
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0,6,7,8])|(14[5,7]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    public static boolean isCarnumberNO(String carnumber) {     /*     车牌号格式：汉字 + A-Z + 位A-Z或-     （只包括了普通车牌号，教练车和部分部队车等车牌号不包括在内）     */
        String carnumRegex;
        carnumRegex = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";
        if (TextUtils.isEmpty(carnumber)) return false;
        else return carnumber.matches(carnumRegex);
    }

    //判断邮箱格式
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
