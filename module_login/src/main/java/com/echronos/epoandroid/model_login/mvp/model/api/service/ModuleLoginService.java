/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.echronos.epoandroid.model_login.mvp.model.api.service;

import com.echronos.epoandroid.model_login.mvp.model.api.Api;
import com.echronos.epoandroid.model_login.mvp.model.entity.LoginResultBean;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * ================================================
 * 展示 {@link Retrofit#create(Class)} 中需要传入的 ApiService 的使用方式
 * 存放关于 zhihu 的一些 API
 * <p>
 * Created by JessYan on 08/05/2016 12:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface ModuleLoginService {

    /**
     * 提交用户登录
     *
     * @param user
     * @param password
     * @param sms_code
     * @param loginType
     * @return
     */
    @FormUrlEncoded
    @Headers({DOMAIN_NAME_HEADER + Api.LOGIN_DOMAIN_NAME})
    @POST("channel/im/app_user_login/")
    Observable<HttpResult<LoginResultBean>> postLoginUserApp(@Field("account") String user, @Field("password") String password, @Field("sms_code") String sms_code, @Field("type") int loginType);

    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @Headers({DOMAIN_NAME_HEADER + Api.LOGIN_DOMAIN_NAME})
    @POST("channel/im/app_Phone_Verification_Code/")
    Observable<HttpResult> sendSmsCode( @Field("phone") String phone,@Field("type") int type);

}
