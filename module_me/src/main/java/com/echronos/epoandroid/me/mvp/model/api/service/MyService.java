package com.echronos.epoandroid.me.mvp.model.api.service;

import com.echronos.epoandroid.me.mvp.model.entity.UserInfo;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import retrofit2.http.GET;

/**
 * @Author :hexingbo
 * @Date :2019/10/4
 * @FileName： MyService
 * @Describe :
 */
public interface MyService {
    /**
     * 获取用户信息
     */
    @GET("channel/getMineMainInfo/")
    Observable<HttpResult<UserInfo>> getUserInfo();
}
