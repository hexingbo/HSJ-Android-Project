package com.echronos.epoandroid.module_order.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerContract;
import com.echronos.epoandroid.module_order.mvp.model.OrderListManagerModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/25 20:16
 * 描    述：
 * =============================================
 */
@Module
public abstract class OrderListManagerModule {

    @Binds
    abstract OrderListManagerContract.Model bindOrderListManagerModel(OrderListManagerModel model);
}