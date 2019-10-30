package com.echronos.epoandroid.model_login.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.model_login.di.module.MainLoginModule;
import com.echronos.epoandroid.model_login.mvp.contract.LoginMainContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.model_login.mvp.ui.activity.LoginMainActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 16:54
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = MainLoginModule.class, dependencies = AppComponent.class)
public interface LoginMainComponent {
    void inject(LoginMainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LoginMainComponent.Builder view(LoginMainContract.View view);

        LoginMainComponent.Builder appComponent(AppComponent appComponent);

        LoginMainComponent build();
    }
}