package com.echronos.epoandroid.model_login.di.module;

import android.app.Dialog;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jessyan.armscomponent.commonres.dialog.ProgresDialog;

import com.echronos.epoandroid.model_login.mvp.contract.MainLoginContract;
import com.echronos.epoandroid.model_login.mvp.model.MainLoginModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 16:54
 * 描    述：
 * =============================================
 */
@Module
public abstract class MainLoginModule {

    @Binds
    abstract MainLoginContract.Model bindMainLoginModel(MainLoginModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(MainLoginContract.View view){
        return new ProgresDialog(view.getActivity());
    }
}