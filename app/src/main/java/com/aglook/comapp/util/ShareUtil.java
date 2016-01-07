package com.aglook.comapp.util;


import android.app.Activity;

import com.aglook.comapp.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * Created by aglook on 2015/12/28.
 */
public class ShareUtil {
    public static void Share(Activity activity,String title,String url) {
//        Log.d("result_share",title+"______"+url);
        UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        // 设置分享内容
        mController.setShareContent("测试内容");
        mController.setShareMedia(new UMImage(activity, R.drawable.ic_launcher));
        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SMS);
//        mController.getConfig().removePlatform(SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA);

        String content = "网通天下杂粮，买卖随时随地";
//        String title = "分享测试title";

        String appID = "wxb7be9be3615de8a9";
        String appSecret = "7485629924082103ae3876cde7e1c51a";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(activity, appID, appSecret);
        wxHandler.showCompressToast(false);
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(activity, appID,
                appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();

        // 设置微信好友分享内容
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        // 设置分享文字
        weixinContent.setShareContent(content);
        // 设置title
        weixinContent.setTitle(title);
        // 设置分享内容跳转URL
        weixinContent.setTargetUrl(url);
        // 设置分享图片
        weixinContent.setShareImage(new UMImage(activity, R.drawable.share_icon));
        mController.setShareMedia(weixinContent);

        // 设置微信朋友圈分享内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent(content);
        // 设置朋友圈title
        circleMedia.setTitle(title);
        circleMedia.setShareImage(new UMImage(activity, R.drawable.share_icon));
        circleMedia.setTargetUrl(url);
        mController.setShareMedia(circleMedia);

        /** 分享qq 参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY. **/
//        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity,
//                "1105045872", "B9Iz7rjOnMMTkVvj");
        String ppId = "1105045872";
        String appKey = "B9Iz7rjOnMMTkVvj";
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity,
                ppId, appKey);
        qqSsoHandler.addToSocialSDK();
        // 设置qq分享内容
        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setShareContent(content);
        qqShareContent.setTitle(title);
        qqShareContent.setShareImage(new UMImage(activity, R.drawable.share_icon));
        qqShareContent.setTargetUrl(url);
        mController.setShareMedia(qqShareContent);
//        // 添加QZone平台
//        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, "1105045872", "B9Iz7rjOnMMTkVvj");
//        qZoneSsoHandler.addToSocialSDK();

        // 设置短信分享内容
        SmsHandler smsHandler = new SmsHandler();
        smsHandler.addToSocialSDK();
        SmsShareContent sms = new SmsShareContent();

        sms.setShareContent(title+url+" "+content);
//        sms.setShareImage(urlImage);
        mController.setShareMedia(sms);


        mController.openShare(activity, false);

    }
}
