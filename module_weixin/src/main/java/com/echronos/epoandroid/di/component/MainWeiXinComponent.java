package com.echronos.epoandroid.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.di.module.MainWeiXinModule;
import com.echronos.epoandroid.mvp.contract.MainWeiXinContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.mvp.ui.activity.MainWeiXinActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:16
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = MainWeiXinModule.class, dependencies = AppComponent.class)
public interface MainWeiXinComponent {
    void inject(MainWeiXinActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainWeiXinComponent.Builder view(MainWeiXinContract.View view);

        MainWeiXinComponent.Builder appComponent(AppComponent appComponent);

        MainWeiXinComponent build();
    }
}