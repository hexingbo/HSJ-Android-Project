package com.echronos.epoandroid.module_order.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.module_order.di.module.OrderListManagerModule;
import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.module_order.mvp.ui.activity.OrderListManagerActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/25 20:16
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = OrderListManagerModule.class, dependencies = AppComponent.class)
public interface OrderListManagerComponent {
    void inject(OrderListManagerActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        OrderListManagerComponent.Builder view(OrderListManagerContract.View view);

        OrderListManagerComponent.Builder appComponent(AppComponent appComponent);

        OrderListManagerComponent build();
    }
}