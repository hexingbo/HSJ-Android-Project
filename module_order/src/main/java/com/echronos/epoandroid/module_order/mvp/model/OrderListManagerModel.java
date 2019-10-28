package com.echronos.epoandroid.module_order.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/25 20:16
 * 描    述：
 * =============================================
 */
@ActivityScope
public class OrderListManagerModel extends BaseModel implements OrderListManagerContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public OrderListManagerModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}