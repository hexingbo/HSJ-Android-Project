package com.echronos.epoandroid.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.di.module.WXEntryModule;
import com.echronos.epoandroid.mvp.contract.WXEntryContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.wxapi.WXEntryActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:25
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = WXEntryModule.class, dependencies = AppComponent.class)
public interface WXEntryComponent {
    void inject(WXEntryActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WXEntryComponent.Builder view(WXEntryContract.View view);

        WXEntryComponent.Builder appComponent(AppComponent appComponent);

        WXEntryComponent build();
    }
}