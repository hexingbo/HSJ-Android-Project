package com.echronos.epoandroid.model_login.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.model_login.di.module.MainRegisterUserModule;
import com.echronos.epoandroid.model_login.mvp.contract.RegisterUserContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.model_login.mvp.ui.activity.RegisterUserActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 11:13
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = MainRegisterUserModule.class, dependencies = AppComponent.class)
public interface RegisterUserComponent {
    void inject(RegisterUserActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        RegisterUserComponent.Builder view(RegisterUserContract.View view);

        RegisterUserComponent.Builder appComponent(AppComponent appComponent);

        RegisterUserComponent build();
    }
}