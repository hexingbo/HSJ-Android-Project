package com.echronos.epoandroid.model_login.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.echronos.epoandroid.model_login.R;
import com.echronos.epoandroid.model_login.R2;
import com.echronos.epoandroid.model_login.di.component.DaggerRegisterUserComponent;
import com.echronos.epoandroid.model_login.mvp.contract.RegisterUserContract;
import com.echronos.epoandroid.model_login.mvp.presenter.RegisterUserPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.armscomponent.commonres.other.ClearEditText;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 11:13
 * 描    述：用户注册
 * =============================================
 */
@Route(path = RouterHub.Loging_MainRegisterUserActivity)
public class RegisterUserActivity extends BaseActivity<RegisterUserPresenter> implements RegisterUserContract.View {

    @BindView(R2.id.public_toolbar_title)
    TextView publicToolbarTitle;
    @BindView(R2.id.et_user)
    ClearEditText etUser;
    @BindView(R2.id.et_checkNumebr)
    ClearEditText etCheckNumebr;
    @BindView(R2.id.tv_send_number)
    TextView tvSendNumber;
    @BindView(R2.id.et_userPwd)
    ClearEditText etUserPwd;
    @BindView(R2.id.btn_submit)
    TextView btnSubmit;
    @BindView(R2.id.cb_isAgree)
    CheckBox cbIsAgree;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRegisterUserComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main_register_user; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.str_title_register_user);
        btnSubmit.setText(R.string.register_lab_submit_btn_value);
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

    @OnClick({ R2.id.tv_send_number, R2.id.btn_submit, R2.id.tv_AgreeInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_number:
                break;
            case R.id.btn_submit:
                break;
            case R.id.tv_AgreeInfo:
                break;
        }
    }
}
