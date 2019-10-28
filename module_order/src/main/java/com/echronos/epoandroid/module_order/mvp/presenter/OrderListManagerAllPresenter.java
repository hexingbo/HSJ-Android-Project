package com.echronos.epoandroid.module_order.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerAllContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 13:41
 * 描    述：
 * =============================================
 */
@FragmentScope
public class OrderListManagerAllPresenter extends BasePresenter<OrderListManagerAllContract.Model, OrderListManagerAllContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public OrderListManagerAllPresenter(OrderListManagerAllContract.Model model, OrderListManagerAllContract.View rootView) {
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
