package com.echronos.epoandroid.module_cart.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartAllContract;
import com.echronos.epoandroid.module_cart.mvp.model.ShoppingCartAllModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：
 * =============================================
 */
@Module
public abstract class ShoppingCartAllModule {

    @Binds
    abstract ShoppingCartAllContract.Model bindShoppingCartAllModel(ShoppingCartAllModel model);
}