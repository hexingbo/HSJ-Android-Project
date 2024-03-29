package com.echronos.epoandroid.model_login.mvp.model;

import android.app.Application;

import com.echronos.epoandroid.model_login.mvp.contract.RegisterUserContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 11:13
 * 描    述：
 * =============================================
 */
@ActivityScope
public class RegisterUserModel extends BaseModel implements RegisterUserContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public RegisterUserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}