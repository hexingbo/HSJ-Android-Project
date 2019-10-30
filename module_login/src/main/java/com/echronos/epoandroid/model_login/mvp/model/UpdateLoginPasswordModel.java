package com.echronos.epoandroid.model_login.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.echronos.epoandroid.model_login.mvp.contract.UpdateLoginPasswordContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 14:10
 * 描    述：
 * =============================================
 */
@ActivityScope
public class UpdateLoginPasswordModel extends BaseModel implements UpdateLoginPasswordContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public UpdateLoginPasswordModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}