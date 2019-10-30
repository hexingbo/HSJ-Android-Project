package com.echronos.epoandroid.module_cart.mvp.presenter;

import android.app.Application;

import com.echronos.epoandroid.module_cart.BuildConfig;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.RepositoryManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import me.jessyan.armscomponent.commonsdk.base.observer.MyHttpResultObserver;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartAllContract;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：
 * =============================================
 */
@FragmentScope
public class ShoppingCartAllPresenter extends BasePresenter<ShoppingCartAllContract.Model, ShoppingCartAllContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ShoppingCartAllPresenter(ShoppingCartAllContract.Model model, ShoppingCartAllContract.View rootView) {
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

    public void postAddGoodsToCart(String sale_id, String number) {
        if (ArmsUtils.isEmpty(sale_id) || ArmsUtils.isEmpty(number)) {
            return;
        }

        mModel.postAddGoodsToCart(sale_id, number)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(
                        //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                        BuildConfig.HTTP_MaxRetries, BuildConfig.HTTP_RetryDelaySecond))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                //使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new MyHttpResultObserver<HttpResult>(mErrorHandler) {

                    @Override
                    public void onResult(HttpResult result) {
                        ArmsUtils.makeText(AppManager.getAppManager().getCurrentActivity(), result.toString());
                    }
                });

    }

    public void postDeleteGoods(List<String> snap_ids) {

    }

    public void postUpdateCartSnapNum(String snap_id, String num) {

    }

    /**
     * 获取购物车列表
     */
    public void getCartList() {

        mModel.getCartList("sale")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(
                        //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                        BuildConfig.HTTP_MaxRetries, BuildConfig.HTTP_RetryDelaySecond))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                //使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new MyHttpResultObserver<HttpResult>(mErrorHandler) {

                    @Override
                    public void onResult(HttpResult result) {
                        mRootView.showMessage(result.toString());
                    }
                });
    }

    public void getGoodsListTuiJian(int page, int pagesize) {

    }

    public void postSaleToCollection(String type, List<String> snap_ids) {

    }
}
