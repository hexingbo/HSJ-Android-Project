package com.echronos.epoandroid.model_login.mvp.model.api;

import com.echronos.epoandroid.model_login.BuildConfig;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * ================================================
 */
public interface Api {

    String LOGIN_DOMAIN_NAME = "login";
    String LOGIN_DOMAIN = BuildConfig.BASE_URL;
//    String LOGIN_DOMAIN = "http://192.168.188.12:10305";

}
