package com.echronos.epoandroid.me.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.me.mvp.contract.MeMainContract;
import com.echronos.epoandroid.me.mvp.model.MeMainModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/09/27 10:04
 * 描    述：
 * =============================================
 */
@Module
public abstract class MeMainModule {

    @Binds
    abstract MeMainContract.Model bindMeMainModel(MeMainModel model);
}