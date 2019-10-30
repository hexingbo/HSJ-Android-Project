package com.echronos.epoandroid.mvp.presenter;

import android.app.Application;

import com.echronos.epoandroid.BuildConfig;
import com.echronos.epoandroid.mvp.interfaces.login.ILoginCallback;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.echronos.epoandroid.mvp.contract.WXEntryContract;
import com.jess.arms.utils.RxLifecycleUtils;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:25
 * 描    述：
 * =============================================
 */
@ActivityScope
public class WXEntryPresenter extends BasePresenter<WXEntryContract.Model, WXEntryContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public WXEntryPresenter(WXEntryContract.Model model, WXEntryContract.View rootView) {
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

    public void getWeixinAccessToken(String code, ILoginCallback callback) {
        mModel.getWeixinAccessToken(code)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(BuildConfig.HTTP_MaxRetries, BuildConfig.HTTP_RetryDelaySecond))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(String resultBean) {
                        if (callback != null) {
                            callback.tokeCallBack(resultBean.toString());
                            AppManager.getAppManager().getCurrentActivity().finish();
                        }
                    }
                });
    }
}
