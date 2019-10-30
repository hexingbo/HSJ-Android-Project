package com.echronos.epoandroid.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.echronos.epoandroid.mvp.contract.MainWeiXinContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:16
 * 描    述：
 * =============================================
 */
@ActivityScope
public class MainWeiXinModel extends BaseModel implements MainWeiXinContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainWeiXinModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}