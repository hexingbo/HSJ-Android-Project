package com.echronos.epoandroid.model_login.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.model_login.di.module.MainRegisterUserModule;
import com.echronos.epoandroid.model_login.mvp.contract.MainRegisterUserContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.model_login.mvp.ui.activity.MainRegisterUserActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 11:13
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = MainRegisterUserModule.class, dependencies = AppComponent.class)
public interface MainRegisterUserComponent {
    void inject(MainRegisterUserActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainRegisterUserComponent.Builder view(MainRegisterUserContract.View view);

        MainRegisterUserComponent.Builder appComponent(AppComponent appComponent);

        MainRegisterUserComponent build();
    }
}