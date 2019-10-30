package com.echronos.epoandroid.model_login.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.model_login.di.module.UpdateLoginPasswordModule;
import com.echronos.epoandroid.model_login.mvp.contract.UpdateLoginPasswordContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.model_login.mvp.ui.activity.UpdateLoginPasswordActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 14:10
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = UpdateLoginPasswordModule.class, dependencies = AppComponent.class)
public interface UpdateLoginPasswordComponent {
    void inject(UpdateLoginPasswordActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        UpdateLoginPasswordComponent.Builder view(UpdateLoginPasswordContract.View view);

        UpdateLoginPasswordComponent.Builder appComponent(AppComponent appComponent);

        UpdateLoginPasswordComponent build();
    }
}