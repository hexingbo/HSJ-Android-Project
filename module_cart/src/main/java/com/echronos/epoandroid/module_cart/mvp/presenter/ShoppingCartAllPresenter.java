package com.echronos.epoandroid.module_cart.mvp.presenter;

import android.app.Application;

import com.echronos.epoandroid.module_cart.BuildConfig;
import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartAllContract;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.armscomponent.commonres.enums.SaleToCollectionType;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import me.jessyan.armscomponent.commonsdk.base.observer.MyHttpResultObserver;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


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

    /**
     * 添加商品到购物车
     *
     * @param sale_id
     * @param number
     */
    public void postAddGoodsToCart(String sale_id, String number) {
        if (ArmsUtils.isEmpty(sale_id) || ArmsUtils.isEmpty(number)) {
            mRootView.showMessage("请选择商品");
            return;
        }
        if (ArmsUtils.isEmpty(number)) {
            mRootView.showMessage("商品数量不能为空");
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

    /**
     * 删除购物车商品
     *
     * @param snap_ids
     */
    public void postDeleteGoods(List<String> snap_ids) {
        if (ArmsUtils.isEmpty(snap_ids)) {
            mRootView.showMessage("请选择删除的商品");
            return;
        }
        mModel.postDeleteGoods(snap_ids)
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

    /**
     * 修改购物车商品数量
     *
     * @param snap_id
     * @param number
     */
    public void postUpdateCartSnapNum(String snap_id, String number) {
        if (ArmsUtils.isEmpty(snap_id)) {
            mRootView.showMessage("请选择修改的商品");
            return;
        }
        if (ArmsUtils.isEmpty(number)) {
            mRootView.showMessage("修改数量不能为空");
            return;
        }
        mModel.postUpdateCartSnapNum(snap_id, number)
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

    /**
     * 获取购物车商品列表
     */
    public void getCartList() {
        mModel.getCartList()
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

    /**
     * 获取商品推荐数据
     *
     * @param page
     * @param pagesize
     */
    public void getGoodsListTuiJian(int page, int pagesize) {
        if (page < 1) {
            mRootView.showMessage("当前页码不能小于1");
            return;
        }
        if (pagesize < 1) {
            mRootView.showMessage("当前页数量不能小于1");
            return;
        }
        mModel.getGoodsListTuiJian(page, pagesize)
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

    /**
     * 添加商品到收藏夹
     *
     * @param snap_ids
     */
    public void postSaleToCollection(List<String> snap_ids) {
        if (ArmsUtils.isEmpty(snap_ids)) {
            mRootView.showMessage("请选择商品");
            return;
        }
        mModel.postSaleToCollection(SaleToCollectionType.SaleToCollection, snap_ids)
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
}
