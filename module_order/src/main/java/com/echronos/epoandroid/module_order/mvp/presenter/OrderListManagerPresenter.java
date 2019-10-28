package com.echronos.epoandroid.module_order.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/25 20:16
 * 描    述：
 * =============================================
 */
@ActivityScope
public class OrderListManagerPresenter extends BasePresenter<OrderListManagerContract.Model, OrderListManagerContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public OrderListManagerPresenter(OrderListManagerContract.Model model, OrderListManagerContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
