package com.echronos.epoandroid.di.module;

import android.app.Dialog;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jessyan.armscomponent.commonres.dialog.ProgresDialog;

import com.echronos.epoandroid.mvp.contract.WXEntryContract;
import com.echronos.epoandroid.mvp.model.WXEntryModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:25
 * 描    述：
 * =============================================
 */
@Module
public abstract class WXEntryModule {

    @Binds
    abstract WXEntryContract.Model bindWXEntryModel(WXEntryModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(WXEntryContract.View view){
        return new ProgresDialog(view.getActivity());
    }

}