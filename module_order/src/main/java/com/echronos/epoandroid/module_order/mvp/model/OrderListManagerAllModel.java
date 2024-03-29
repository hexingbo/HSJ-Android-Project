package com.echronos.epoandroid.module_order.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerAllContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 13:41
 * 描    述：
 * =============================================
 */
@FragmentScope
public class OrderListManagerAllModel extends BaseModel implements OrderListManagerAllContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public OrderListManagerAllModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}