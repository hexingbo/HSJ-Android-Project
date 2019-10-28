package com.echronos.epoandroid.module_order.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.echronos.epoandroid.module_order.R;
import com.echronos.epoandroid.module_order.di.component.DaggerOrderListManagerComponent;
import com.echronos.epoandroid.module_order.mvp.contract.OrderListManagerContract;
import com.echronos.epoandroid.module_order.mvp.presenter.OrderListManagerPresenter;
import com.echronos.epoandroid.module_order.mvp.ui.fragment.OrderListManagerAllFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.armscomponent.commonres.utils.MyFragmentUtils;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/25 20:16
 * 描    述：订单列表管理
 * =============================================
 */
public class OrderListManagerActivity extends BaseActivity<OrderListManagerPresenter> implements OrderListManagerContract.View {

    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerOrderListManagerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order_list_manager; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        ArmsUtils.statuInScreen(this);//全屏,并且沉侵式状态栏
        setTitle(R.string.public_order_manager);
        MyFragmentUtils.addFragment(getSupportFragmentManager(),new OrderListManagerAllFragment(),R.id.fl_content);
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


    @OnClick(R.id.public_toolbar_back)
    public void onViewClicked() {
        finish();
    }
}
