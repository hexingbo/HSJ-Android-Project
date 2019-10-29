package com.echronos.epoandroid.model_login.mvp.contract;

import android.content.Context;

import com.echronos.epoandroid.model_login.mvp.model.entity.LoginResultBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonsdk.base.enum_type.LoginType;
import me.jessyan.armscomponent.commonsdk.base.enum_type.SmsCodeType;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28 16:54
 * 描    述：
 * =============================================
 */
public interface MainLoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        //设置切换登录方式界面对应显示
        void changeLoginTypeView(LoginType loginType);

        Context getActivity();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable sendSMSCode(String phone, SmsCodeType smsCodeType);

        Observable<LoginResultBean> postLoginUserApp(String user, String pwd, String sms, LoginType loginType);
    }
}
