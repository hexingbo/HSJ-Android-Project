package com.echronos.epoandroid.util;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29
 * 描    述：登录appid 及 key数据
 * =============================================
 */
public class WxLSUtil {

    private static IWXAPI mIWXAPI;

    public static void init(Context context, String wechatAppId) {
        if (!TextUtils.isEmpty(wechatAppId)) {
            mIWXAPI = WXAPIFactory.createWXAPI(context, wechatAppId);
            mIWXAPI.registerApp(wechatAppId);
        }
    }

    public static boolean isWXAppInstalled(IWXAPI msgApi) {
        return msgApi.isWXAppInstalled();
    }

    public static IWXAPI getmIWXAPI() {
        return mIWXAPI;
    }

    public static void detach() {
        mIWXAPI.detach();
    }

}
