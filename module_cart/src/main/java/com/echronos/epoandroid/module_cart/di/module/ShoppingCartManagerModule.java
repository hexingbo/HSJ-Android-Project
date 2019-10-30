package com.echronos.epoandroid.module_cart.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartManagerContract;
import com.echronos.epoandroid.module_cart.mvp.model.ShoppingCartManagerModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：
 * =============================================
 */
@Module
public abstract class ShoppingCartManagerModule {

    @Binds
    abstract ShoppingCartManagerContract.Model bindShoppingCartManagerModel(ShoppingCartManagerModel model);
}