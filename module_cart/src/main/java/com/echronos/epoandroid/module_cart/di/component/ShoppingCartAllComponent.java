package com.echronos.epoandroid.module_cart.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.module_cart.di.module.ShoppingCartAllModule;
import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartAllContract;

import com.jess.arms.di.scope.FragmentScope;
import com.echronos.epoandroid.module_cart.mvp.ui.fragment.ShoppingCartAllFragment;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：
 * =============================================
 */
@FragmentScope
@Component(modules = ShoppingCartAllModule.class, dependencies = AppComponent.class)
public interface ShoppingCartAllComponent {
    void inject(ShoppingCartAllFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ShoppingCartAllComponent.Builder view(ShoppingCartAllContract.View view);

        ShoppingCartAllComponent.Builder appComponent(AppComponent appComponent);

        ShoppingCartAllComponent build();
    }
}