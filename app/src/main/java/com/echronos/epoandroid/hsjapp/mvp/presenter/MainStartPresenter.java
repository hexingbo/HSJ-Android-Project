package com.echronos.epoandroid.hsjapp.mvp.presenter;

import android.app.Application;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.echronos.epoandroid.hsjapp.mvp.contract.MainStartContract;

import java.util.ArrayList;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/02 16:30
 * 描    述：
 * =============================================
 */
@ActivityScope
public class MainStartPresenter extends BasePresenter<MainStartContract.Model, MainStartContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainStartPresenter(MainStartContract.Model model, MainStartContract.View rootView) {
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

    public ArrayList<CustomTabEntity> getTabEntity(){
        return mModel.getTabEntity();
    }


}
