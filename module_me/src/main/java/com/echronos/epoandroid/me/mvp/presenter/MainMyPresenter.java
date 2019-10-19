package com.echronos.epoandroid.me.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.echronos.epoandroid.me.mvp.contract.MainMyContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/09/27 11:24
 * 描    述：
 * =============================================
 */
@FragmentScope
public class MainMyPresenter extends BasePresenter<MainMyContract.Model, MainMyContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainMyPresenter(MainMyContract.Model model, MainMyContract.View rootView) {
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
