package com.echronos.epoandroid.model_login.di.module;

import com.echronos.epoandroid.model_login.mvp.contract.MainRegisterUserContract;
import com.echronos.epoandroid.model_login.mvp.model.MainRegisterUserModel;

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
    abstract MainRegisterUserContract.Model bindMainRegisterUserModel(MainRegisterUserModel model);
}