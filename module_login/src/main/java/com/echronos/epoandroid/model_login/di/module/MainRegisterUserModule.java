package com.echronos.epoandroid.model_login.di.module;

import com.echronos.epoandroid.model_login.mvp.contract.RegisterUserContract;
import com.echronos.epoandroid.model_login.mvp.model.RegisterUserModel;

import dagger.Binds;
import dagger.Module;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 11:13
 * 描    述：
 * =============================================
 */
@Module
public abstract class MainRegisterUserModule {

    @Binds
    abstract RegisterUserContract.Model bindMainRegisterUserModel(RegisterUserModel model);
}