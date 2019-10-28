package com.echronos.epoandroid.module_order.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerAllContract;
import com.echronos.epoandroid.module_order.mvp.model.OrderListManagerAllModel;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 13:41
 * 描    述：
 * =============================================
 */
@Module
public abstract class OrderListManagerAllModule {

    @Binds
    abstract OrderListManagerAllContract.Model bindOrderListManagerAllModel(OrderListManagerAllModel model);
}