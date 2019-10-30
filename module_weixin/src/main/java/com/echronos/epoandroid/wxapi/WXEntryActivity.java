package com.echronos.epoandroid.wxapi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.echronos.epoandroid.R;
import com.echronos.epoandroid.di.component.DaggerWXEntryComponent;
import com.echronos.epoandroid.mvp.contract.WXEntryContract;
import com.echronos.epoandroid.mvp.interfaces.login.ILoginCallback;
import com.echronos.epoandroid.mvp.interfaces.share.IShareCallBack;
import com.echronos.epoandroid.mvp.manager.WechatShareManager;
import com.echronos.epoandroid.mvp.manager.WeiXinLoginManager;
import com.echronos.epoandroid.mvp.model.api.Api;
import com.echronos.epoandroid.mvp.presenter.WXEntryPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import javax.inject.Inject;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:25
 * 描    述：
 * =============================================
 */
public class WXEntryActivity extends BaseActivity<WXEntryPresenter> implements WXEntryContract.View, IWXAPIEventHandler {

    private static IWXAPI wxAPI;
    private static ILoginCallback sILoginCallback;
    private static IShareCallBack sIShareCallBack;

    @Inject
    Dialog mDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWXEntryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
//        return R.layout.activity_wxentry; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return 0; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        sILoginCallback = WeiXinLoginManager.getiLoginCallback();
        sIShareCallBack = WechatShareManager.getIShareCallBack();
        wxAPI = WXAPIFactory.createWXAPI(this, Api.WECHAT_APPID, true); //第二个参数是指你应用在微信开放平台上的AppID
        wxAPI.registerApp(Api.WECHAT_APPID); // 将该app注册到微信
        wxAPI.handleIntent(getIntent(), this);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        wxAPI.handleIntent(getIntent(), this);
        LogUtils.debugInfo("WXEntryActivity onNewIntent");
    }

    @Override
    public void onReq(BaseReq arg0) {
        LogUtils.debugInfo("WXEntryActivity onReq:" + arg0);
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {//分享
            LogUtils.debugInfo("微信分享操作.....");
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    ArmsUtils.makeText(this, "微信分享成功.....");
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://分享取消
                    ArmsUtils.makeText(this, "微信分享取消.....");
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://分享被拒绝
                    ArmsUtils.makeText(this, "微信分享被拒绝.....");
                    break;
            }
            finish();
        } else if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {//登陆
            LogUtils.debugInfo("微信登录操作.....");
            SendAuth.Resp authResp = (SendAuth.Resp) resp;
            if (!ArmsUtils.isEmpty(authResp.code)) {
                LogUtils.debugInfo( "授权成功.....");
                mPresenter.getWeixinAccessToken(authResp.code, sILoginCallback);
            } else {
                ArmsUtils.makeText(this, "授权失败.....");
                if (sILoginCallback != null) {
                    sILoginCallback.onError(new Exception("授权失败"));
                    finish();
                }
            }
        } else if (resp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
            //移动应用跳转到小程序回调
            WXLaunchMiniProgram.Resp launchMiniProResp = (WXLaunchMiniProgram.Resp) resp;
            String extraData = launchMiniProResp.extMsg; //对应小程序组件 <button open-type="launchApp"> 中的 app-parameter 属性
            LogUtils.debugInfo("移动应用跳转到小程序回调：" + extraData);
            finish();
        }

    }

    @Override
    public Context getActivity() {
        return this;
    }
}
