package com.echronos.epoandroid.model_login.mvp.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.echronos.epoandroid.model_login.R2;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.echronos.epoandroid.model_login.di.component.DaggerMainLoginComponent;
import com.echronos.epoandroid.model_login.mvp.contract.MainLoginContract;
import com.echronos.epoandroid.model_login.mvp.presenter.MainLoginPresenter;

import com.echronos.epoandroid.model_login.R;
import com.jess.arms.utils.DataHelper;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.armscomponent.commonres.other.ClearEditText;
import me.jessyan.armscomponent.commonsdk.base.enum_type.LoginType;
import me.jessyan.armscomponent.commonsdk.core.Constants;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 16:54
 * 描    述：登录
 * =============================================
 */
public class MainLoginActivity extends BaseActivity<MainLoginPresenter> implements MainLoginContract.View {

    @BindView(R2.id.et_userPhone)
    ClearEditText etUserPhone;
    @BindView(R2.id.tv_send_number)
    TextView tvSendNumber;
    @BindView(R2.id.et_userPwd)
    ClearEditText etUserPwd;
    @BindView(R2.id.lv_input_pwd)
    LinearLayout lvInputPwd;
    @BindView(R2.id.et_checkNumebr)
    ClearEditText etCheckNumebr;
    @BindView(R2.id.lv_input_checkNumber)
    LinearLayout lvInputCheckNumber;

    @BindView(R2.id.lv_login_type_wx)
    LinearLayout lvLoginTypeWx;
    @BindView(R2.id.lv_login_type_pwd)
    LinearLayout lvLoginTypePwd;
    @BindView(R2.id.lv_login_type_sms)
    LinearLayout lvLoginTypeSms;

    @Inject
    Dialog mDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ArmsUtils.statuInScreen(this);//全屏,并且沉侵式状态栏
        etUserPhone.setText(DataHelper.getStringSF(this, Constants.sp_login_user));
        changeLoginTypeView(mPresenter.getLoginType());
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
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
    public void changeLoginTypeView(LoginType loginType) {
        lvLoginTypeSms.setVisibility(View.GONE);
        lvLoginTypePwd.setVisibility(View.GONE);
        lvInputPwd.setVisibility(View.GONE);
        lvInputCheckNumber.setVisibility(View.GONE);
        tvSendNumber.setVisibility(View.GONE);
        switch (loginType) {
            case LoginType_Pwd:
                lvLoginTypeSms.setVisibility(View.VISIBLE);
                lvInputPwd.setVisibility(View.VISIBLE);
                break;
            case LoginType_SmsCode:

                lvLoginTypePwd.setVisibility(View.VISIBLE);
                lvInputCheckNumber.setVisibility(View.VISIBLE);
                tvSendNumber.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public Context getActivity() {
        return this;
    }

    @OnClick(R2.id.btn_submit)
    public void onClickSubmitLoginView() {
        // TODO: 2019/10/28 确认登录
        mPresenter.postLoginUserApp(etUserPhone.getText().toString().trim(),
                etUserPwd.getText().toString().trim(), etCheckNumebr.getText().toString().trim());
    }

    @OnClick(R2.id.tv_send_number)
    public void onClickSendSmsCodeView() {
        // TODO: 2019/10/28 发送短信验证码
        mPresenter.sendSMSCode(etUserPhone.getText().toString().trim());
    }

    @OnClick(R2.id.tv_forget_pwd)
    public void onClickForgetPwdView() {
        // TODO: 2019/10/28 忘记密码

    }

    @OnClick(R2.id.tv_phone_register)
    public void onClickRegisterUserView() {
        // TODO: 2019/10/28 用户注册

    }

    @OnClick(R2.id.lv_login_type_wx)
    public void onClickLoginTypeWxView() {
        // TODO: 2019/10/28 微信登录方式

    }

    @OnClick(R2.id.lv_login_type_pwd)
    public void onClickLoginTypePwdView() {
        // TODO: 2019/10/28 密码登录方式
        mPresenter.setLoginType(LoginType.LoginType_Pwd);
    }

    @OnClick(R2.id.lv_login_type_sms)
    public void onClickLoginTypeSmsCodeView() {
        // TODO: 2019/10/28 短信验证登录方式
        mPresenter.setLoginType(LoginType.LoginType_SmsCode);
    }
}
