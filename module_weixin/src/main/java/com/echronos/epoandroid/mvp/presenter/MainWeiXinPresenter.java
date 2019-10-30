package com.echronos.epoandroid.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.echronos.epoandroid.mvp.contract.MainWeiXinContract;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:16
 * 描    述：
 * =============================================
 */
@ActivityScope
public class MainWeiXinPresenter extends BasePresenter<MainWeiXinContract.Model, MainWeiXinContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainWeiXinPresenter(MainWeiXinContract.Model model, MainWeiXinContract.View rootView) {
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
