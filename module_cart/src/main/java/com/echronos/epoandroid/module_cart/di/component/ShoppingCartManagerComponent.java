package com.echronos.epoandroid.module_cart.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.module_cart.di.module.ShoppingCartManagerModule;
import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartManagerContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.module_cart.mvp.ui.activity.ShoppingCartManagerActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = ShoppingCartManagerModule.class, dependencies = AppComponent.class)
public interface ShoppingCartManagerComponent {
    void inject(ShoppingCartManagerActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ShoppingCartManagerComponent.Builder view(ShoppingCartManagerContract.View view);

        ShoppingCartManagerComponent.Builder appComponent(AppComponent appComponent);

        ShoppingCartManagerComponent build();
    }
}