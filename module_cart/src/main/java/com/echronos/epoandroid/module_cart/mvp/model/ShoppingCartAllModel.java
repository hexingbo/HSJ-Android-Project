package com.echronos.epoandroid.module_cart.mvp.model;

import android.app.Application;

import com.echronos.epoandroid.module_cart.mvp.model.api.service.ModuleCartService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartAllContract;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import me.jessyan.armscomponent.commonsdk.base.bean.RequestBodyBean;
import me.jessyan.armscomponent.commonsdk.utils.RequestBodyUtil;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：
 * =============================================
 */
@FragmentScope
public class ShoppingCartAllModel extends BaseModel implements ShoppingCartAllContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ShoppingCartAllModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<HttpResult> postAddGoodsToCart(String sale_id, String number) {
        return mRepositoryManager.obtainRetrofitService(ModuleCartService.class).postAddGoodsToCart(sale_id, number);
    }

    @Override
    public Observable<HttpResult> postDeleteGoods(List<String> snap_ids) {
        String ids = snap_ids.toString().substring(1, snap_ids.toString().length() - 1);
        return mRepositoryManager.obtainRetrofitService(ModuleCartService.class).postDeleteGoods(ids);
    }

    @Override
    public Observable<HttpResult> postUpdateCartSnapNum(String snap_id, String num) {
        return mRepositoryManager.obtainRetrofitService(ModuleCartService.class).postUpdateCartSnapNum(snap_id, num);
    }

    @Override
    public Observable<HttpResult> getCartList(String cat_type) {
        return mRepositoryManager.obtainRetrofitService(ModuleCartService.class).getCartList(cat_type);
    }

    @Override
    public Observable<HttpResult> getGoodsListTuiJian(int page, int pagesize) {
        return mRepositoryManager.obtainRetrofitService(ModuleCartService.class).getGoodsListTuiJian(page, pagesize);
    }

    @Override
    public Observable<HttpResult> postSaleToCollection(String type, List<String> snap_ids) {
        Map<String, List<String>> mapList = new HashMap<>();
        mapList.put("id", snap_ids);
        return mRepositoryManager.obtainRetrofitService(ModuleCartService.class).postSaleToCollection(type,
                RequestBodyUtil.getRequestBodyValue(new RequestBodyBean(mapList, null)));
    }
}