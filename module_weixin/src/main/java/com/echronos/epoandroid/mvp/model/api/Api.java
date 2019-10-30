package com.echronos.epoandroid.mvp.model.api;

import com.echronos.epoandroid.BuildConfig;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * ================================================
 */
public interface Api {
    String LOGIN_DOMAIN_NAME = "login";
    //    String LOGIN_DOMAIN = BuildConfig.BASE_URL;
    String LOGIN_DOMAIN = "https://api.weixin.qq.com";
    String WECHAT_APPID = BuildConfig.WX_APPID;
    String WX_SECRET = BuildConfig.WX_SECRET;


}
