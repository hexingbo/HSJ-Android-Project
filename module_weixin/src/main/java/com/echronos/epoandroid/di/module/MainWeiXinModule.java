package com.echronos.epoandroid.di.module;

import com.echronos.epoandroid.mvp.contract.MainWeiXinContract;
import com.echronos.epoandroid.mvp.model.MainWeiXinModel;

import dagger.Binds;
import dagger.Module;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:16
 * 描    述：
 * =============================================
 */
@Module
public abstract class MainWeiXinModule {

    @Binds
    abstract MainWeiXinContract.Model bindMainWeiXinModel(MainWeiXinModel model);
}