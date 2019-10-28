package com.echronos.epoandroid.module_order.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.module_order.di.module.OrderListManagerAllModule;
import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerAllContract;

import com.jess.arms.di.scope.FragmentScope;
import com.echronos.epoandroid.module_order.mvp.ui.fragment.OrderListManagerAllFragment;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 13:41
 * 描    述：
 * =============================================
 */
@FragmentScope
@Component(modules = OrderListManagerAllModule.class, dependencies = AppComponent.class)
public interface OrderListManagerAllComponent {
    void inject(OrderListManagerAllFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        OrderListManagerAllComponent.Builder view(OrderListManagerAllContract.View view);

        OrderListManagerAllComponent.Builder appComponent(AppComponent appComponent);

        OrderListManagerAllComponent build();
    }
}