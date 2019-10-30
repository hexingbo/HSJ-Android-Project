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
package com.echronos.epoandroid.module_cart.mvp.model.api.service;


import com.echronos.epoandroid.module_cart.mvp.model.api.Api;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
public interface ModuleCartService {

    /**
     * 添加商品到购物车
     *
     * @param sale_id
     * @param number
     * @return
     */
    @FormUrlEncoded
    @Headers({DOMAIN_NAME_HEADER + Api.CART_DOMAIN_NAME})
    @POST("channel/v2/addSaleToCart/")
    Observable<HttpResult> postAddGoodsToCart(@Field("sale_id") String sale_id, @Field("num") String number);

    /**
     * 删除购物车商品数量
     *
     * @param ids
     * @return
     */
    @FormUrlEncoded
    @Headers({DOMAIN_NAME_HEADER + Api.CART_DOMAIN_NAME})
    @POST("channel/v2/deleteCart/")
    Observable<HttpResult> postDeleteGoods(@Field("ids") String ids);

    /**
     * 修改购物车商品数量
     *
     * @param snap_id
     * @param num
     * @return
     */
    @FormUrlEncoded
    @Headers({DOMAIN_NAME_HEADER + Api.CART_DOMAIN_NAME})
    @POST("channel/v2/updateSaleCartNum/")
    Observable<HttpResult> postUpdateCartSnapNum(@Field("snap_id") String snap_id, @Field("num") String num);

    /**
     * 购物车列表
     *
     * @param cat_type
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + Api.CART_DOMAIN_NAME})
    @GET("channel/v2/cartList/")
    Observable<HttpResult> getCartList(@Query("cat_type") String cat_type);

    /**
     * 推荐的商品
     *
     * @param page
     * @param pagesize
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + Api.CART_DOMAIN_NAME})
    @GET("channel/sale/recommended/")
    Observable<HttpResult> getGoodsListTuiJian(@Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 添加商品到收藏夹
     *
     * @param type
     * @param body
     * @return
     */
    @FormUrlEncoded
    @Headers({DOMAIN_NAME_HEADER + Api.CART_DOMAIN_NAME})
    @POST("channel/deport/sale_collection/")
    Observable<HttpResult> postSaleToCollection(@Field("type") String type, @Body RequestBody body);


}
