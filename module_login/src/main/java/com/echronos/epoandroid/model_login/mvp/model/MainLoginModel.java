package com.echronos.epoandroid.model_login.mvp.model;

import android.app.Application;

import com.echronos.epoandroid.model_login.mvp.model.api.service.ModuleLoginService;
import com.echronos.epoandroid.model_login.mvp.model.entity.LoginResultBean;
import com.echronos.epoandroid.model_login.mvp.ui.activity.MainRegisterUserActivity;
import com.google.gson.Gson;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.echronos.epoandroid.model_login.mvp.contract.MainLoginContract;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import me.jessyan.armscomponent.commonsdk.base.enum_type.LoginType;
import me.jessyan.armscomponent.commonsdk.base.enum_type.SmsCodeType;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 16:54
 * 描    述：
 * =============================================
 */
@ActivityScope
public class MainLoginModel extends BaseModel implements MainLoginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainLoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable sendSMSCode(String phone, SmsCodeType smsCodeType) {
        int smsCode = smsCodeType == SmsCodeType.SmsCodeType_Login ? 2 : 2;
        return mRepositoryManager.obtainRetrofitService(ModuleLoginService.class).sendSmsCode(phone, smsCode);
    }

    @Override
    public Observable<HttpResult<LoginResultBean>> postLoginUserApp(String user, String pwd, String smsCode, LoginType loginType) {
        return mRepositoryManager.obtainRetrofitService(ModuleLoginService.class).postLoginUserApp(user, pwd, smsCode, loginType == LoginType.LoginType_SmsCode ? 2 : 1);
    }

    @Override
    public void goMainRegisterUserActivity() {
        AppManager.getAppManager().startActivity(MainRegisterUserActivity.class);
    }

    @Override
    public void goMainForgetPasswordActivity() {

    }
}