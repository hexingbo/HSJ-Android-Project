package com.echronos.epoandroid.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.echronos.epoandroid.R;
import com.echronos.epoandroid.R2;
import com.echronos.epoandroid.di.component.DaggerMainWeiXinComponent;
import com.echronos.epoandroid.mvp.contract.MainWeiXinContract;
import com.echronos.epoandroid.mvp.interfaces.login.ILoginCallback;
import com.echronos.epoandroid.mvp.manager.WeiXinLoginManager;
import com.echronos.epoandroid.mvp.presenter.MainWeiXinPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:16
 * 描    述：
 * =============================================
 */
public class MainWeiXinActivity extends BaseActivity<MainWeiXinPresenter> implements MainWeiXinContract.View {

    @BindView(R.id.public_toolbar_back)
    RelativeLayout publicToolbarBack;
    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;
    @BindView(R.id.tv_value)
    TextView tvValue;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainWeiXinComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main_wei_xin; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.app_name);
        tvValue.setText(AppManager.getAppManager().getAppProcessName(this));
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


    @OnClick({R.id.public_toolbar_back, R.id.btn_weixin_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_toolbar_back:
                finish();
                break;
            case R.id.btn_weixin_login:
                new WeiXinLoginManager(getApplication()).doLogin(new ILoginCallback() {
                    @Override
                    public void tokeCallBack(Object o) {
                        ArmsUtils.makeText(getApplicationContext(), o.toString());
                    }

                    @Override
                    public void infoCallBack(Object o) {

                    }

                    @Override
                    public void onError(Exception e) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
        }
    }
}
