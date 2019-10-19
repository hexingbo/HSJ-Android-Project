package com.echronos.epoandroid.hsjapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.hsjapp.mvp.contract.MainStartContract;
import com.echronos.epoandroid.hsjapp.mvp.model.MainStartModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/02 16:30
 * 描    述：
 * =============================================
 */
@Module
public abstract class MainStartModule {

    @Binds
    abstract MainStartContract.Model bindMainStartModel(MainStartModel model);
}