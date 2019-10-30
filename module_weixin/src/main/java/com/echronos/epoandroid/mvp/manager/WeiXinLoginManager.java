package com.echronos.epoandroid.mvp.manager;

import android.content.Context;
import android.content.Intent;

import com.echronos.epoandroid.mvp.interfaces.login.ILogin;
import com.echronos.epoandroid.mvp.interfaces.login.ILoginCallback;
import com.echronos.epoandroid.mvp.model.api.Api;
import com.echronos.epoandroid.util.WxLSUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created on 2016/3/25.下午10:00.
 *
 * @author bobomee.
 * wbwjx115@gmail.com
 */
public class WeiXinLoginManager implements ILogin {

    // 微信相关
    private static IWXAPI api;
    private static ILoginCallback iLoginCallback;

    public WeiXinLoginManager(Context _activity) {
        WxLSUtil.init(_activity, Api.WECHAT_APPID);
        api = WxLSUtil.getmIWXAPI();
    }

    @Override
    public void doLogin(ILoginCallback callback) {
        iLoginCallback = callback;
        login();
    }

    private void login() {
        if (WxLSUtil.isWXAppInstalled(api)) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo";
            api.sendReq(req);//第三方发送消息给微信。
        } else {
            if (null != iLoginCallback) iLoginCallback.onError(new Exception("not support"));
        }
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (iLoginCallback != null) {
            return true;
        }
        return false;
    }

    public static IWXAPI getApi() {
        return api;
    }

    public static ILoginCallback getiLoginCallback() {
        return iLoginCallback;
    }

    public static void setLoginCallback(ILoginCallback callback) {
        iLoginCallback = callback;
    }

}
