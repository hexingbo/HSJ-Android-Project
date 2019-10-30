package com.echronos.epoandroid.model_login.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.echronos.epoandroid.model_login.di.component.DaggerUpdateLoginPasswordComponent;
import com.echronos.epoandroid.model_login.mvp.contract.UpdateLoginPasswordContract;
import com.echronos.epoandroid.model_login.mvp.presenter.UpdateLoginPasswordPresenter;

import com.echronos.epoandroid.model_login.R;


import me.jessyan.armscomponent.commonsdk.core.RouterHub;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 14:10
 * 描    述：修改登录密码
 * =============================================
 */
@Route(path = RouterHub.Loging_UpdateLoginPasswordActivity)
public class UpdateLoginPasswordActivity extends BaseActivity<UpdateLoginPasswordPresenter> implements UpdateLoginPasswordContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUpdateLoginPasswordComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_update_login_password; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
