package com.echronos.epoandroid.model_login.mvp.presenter;

import android.app.Application;

import com.echronos.epoandroid.model_login.BuildConfig;
import com.echronos.epoandroid.model_login.mvp.contract.LoginMainContract;
import com.echronos.epoandroid.model_login.mvp.model.entity.LoginResultBean;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import me.jessyan.armscomponent.commonsdk.base.enum_type.LoginType;
import me.jessyan.armscomponent.commonsdk.base.enum_type.SmsCodeType;
import me.jessyan.armscomponent.commonsdk.base.observer.MyHttpResultObserver;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 16:54
 * 描    述：
 * =============================================
 */
@ActivityScope
public class LoginMainPresenter extends BasePresenter<LoginMainContract.Model, LoginMainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginMainPresenter(LoginMainContract.Model model, LoginMainContract.View rootView) {
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

    private LoginType loginType = LoginType.LoginType_Pwd;

    /**
     * 设置登录方式+并改变界面view（密码/手机号）
     *
     * @param type
     */
    public void setLoginType(LoginType type) {
        loginType = type;
        mRootView.changeLoginTypeView(loginType);
    }

    /**
     * 获取当前的登录方式（密码/手机号）
     *
     * @return
     */
    public LoginType getLoginType() {
        return loginType;
    }

    /**
     * 用户登录
     *
     * @param user
     * @param pwd
     * @param sms
     */
    public void postLoginUserApp(String user, String pwd, String sms) {
        if (ArmsUtils.isEmpty(user)) {
            ArmsUtils.snackbarText("请输入用户名");
            return;
        }
        switch (loginType) {
            case LoginType_SmsCode:
                if (ArmsUtils.isEmpty(sms)) {
                    ArmsUtils.snackbarText("请输入验证码");
                    return;
                }
                if (sms.length() != 6) {
                    ArmsUtils.snackbarText("请输入6位验证码");
                    return;
                }
                break;
            case LoginType_Pwd:
                if (ArmsUtils.isEmpty(pwd)) {
                    ArmsUtils.snackbarText("请输入密码");
                    return;
                }
                break;
        }
        mModel.postLoginUserApp(user, pwd, sms, loginType)
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
                .subscribe(new MyHttpResultObserver<HttpResult<LoginResultBean>>(mErrorHandler) {

                    @Override
                    public void onResult(HttpResult<LoginResultBean> result) {
                        ArmsUtils.makeText(mRootView.getActivity(), result.toString());
                    }
                });
    }

    /**
     * 获取短信验证码
     *
     * @param phone
     */
    public void sendSMSCode(String phone) {
        if (ArmsUtils.isEmpty(phone)) {
            ArmsUtils.snackbarText("请输入手机号码");
            return;
        }
        mModel.sendSMSCode(phone, SmsCodeType.SmsCodeType_Login)
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
                .subscribe(new MyHttpResultObserver<HttpResult>(mErrorHandler) {
                    @Override
                    public void onResult(HttpResult result) {

                    }
                });
    }

    /**
     * 注册新用户
     */
    public void goMainRegisterUserActivity(){
        mModel.goMainRegisterUserActivity();
    }
    /**
     * 找回密码
     */
    public void goMainForgetPasswordActivity(){
        mModel.goMainForgetPasswordActivity();
    }

}
