package com.echronos.epoandroid.module_cart.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.echronos.epoandroid.module_cart.R2;
import com.echronos.epoandroid.module_cart.mvp.ui.fragment.ShoppingCartAllFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.echronos.epoandroid.module_cart.di.component.DaggerShoppingCartManagerComponent;
import com.echronos.epoandroid.module_cart.mvp.contract.ShoppingCartManagerContract;
import com.echronos.epoandroid.module_cart.mvp.presenter.ShoppingCartManagerPresenter;

import com.echronos.epoandroid.module_cart.R;


import butterknife.OnClick;
import me.jessyan.armscomponent.commonres.utils.MyFragmentUtils;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：购物车Activity
 * =============================================
 */
@Route(path = RouterHub.Cart_ShoppingCartManagerActivity)
public class ShoppingCartManagerActivity extends BaseActivity<ShoppingCartManagerPresenter> implements ShoppingCartManagerContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerShoppingCartManagerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shopping_cart_manager; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.public_shopping_cart);
        MyFragmentUtils.addFragment(getSupportFragmentManager(),  ShoppingCartAllFragment.newInstance(), R.id.fl_content);
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

    @Override
    public void onBackPressed() {
        finish();
    }
}
