package me.jessyan.armscomponent.commonsdk.base.observer;

import com.jess.arms.utils.DataHelper;

import me.jessyan.armscomponent.commonsdk.base.app.MyBaseApplication;
import me.jessyan.armscomponent.commonsdk.base.bean.MyHttpResult;
import me.jessyan.armscomponent.commonsdk.core.Constants;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * @Author :hexingbo
 * @Date :2019/10/6
 * @FileName： MyHttpResultObserver
 * @Describe :
 */
public abstract class MyHttpResultObserver<T extends MyHttpResult> extends ErrorHandleSubscriber<T> {

    public MyHttpResultObserver(RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
    }

    @Override
    public void onNext(T httpResult) {
        switch (HttpResultEnum.getCodeType(httpResult.getErrcode())) {
            case Success_Code://请求成功
                onResult(httpResult);
                break;
            case Login_Error_Code://登录失效
                onError(new Throwable("登录失效，请重新登录"));
                DataHelper.setStringSF(MyBaseApplication.mContext, Constants.SP_TOKEN, "");
//                Utils.navigation(MyBaseApplication.mContext, RouterHub.Login_Activity_LoginActivity);
                break;
            case Other_Code://其他
                onError(new Throwable(httpResult.getMsg()));
                break;
        }
    }

    public abstract void onResult(T result);

    public enum HttpResultEnum {
        Success_Code, Login_Error_Code, Other_Code;

        public static HttpResultEnum getCodeType(int code) {
            HttpResultEnum resultEnum;
            switch (code) {
                case 0:
                    resultEnum = Success_Code;
                    break;
                case 4:
                    resultEnum = Login_Error_Code;
                    break;
                default:
                    resultEnum = Other_Code;
                    break;
            }
            return resultEnum;
        }
    }
}
