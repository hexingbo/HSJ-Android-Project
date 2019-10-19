package com.echronos.epoandroid.me.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.me.mvp.contract.MainMyContract;
import com.echronos.epoandroid.me.mvp.model.MainMyModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/09/27 11:24
 * 描    述：
 * =============================================
 */
@Module
public abstract class MainMyModule {

    @Binds
    abstract MainMyContract.Model bindMainMyModel(MainMyModel model);
}