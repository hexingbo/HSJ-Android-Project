package com.echronos.epoandroid.mvp.model;

import android.app.Application;

import com.echronos.epoandroid.mvp.model.api.Api;
import com.echronos.epoandroid.mvp.model.api.service.ModuleWeixinService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.echronos.epoandroid.mvp.contract.WXEntryContract;

import io.reactivex.Observable;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:25
 * 描    述：
 * =============================================
 */
@ActivityScope
public class WXEntryModel extends BaseModel implements WXEntryContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WXEntryModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<String> getWeixinAccessToken( String code) {
        return mRepositoryManager.obtainRetrofitService(ModuleWeixinService.class).getWeixinAccessToken(Api.WECHAT_APPID,Api.WX_SECRET,code,"authorization_code");
    }
}