package com.echronos.epoandroid.model_login.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.model_login.mvp.contract.UpdateLoginPasswordContract;
import com.echronos.epoandroid.model_login.mvp.model.UpdateLoginPasswordModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 14:10
 * 描    述：
 * =============================================
 */
@Module
public abstract class UpdateLoginPasswordModule {

    @Binds
    abstract UpdateLoginPasswordContract.Model bindUpdateLoginPasswordModel(UpdateLoginPasswordModel model);
}