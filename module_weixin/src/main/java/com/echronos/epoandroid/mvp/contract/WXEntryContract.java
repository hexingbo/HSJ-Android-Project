package com.echronos.epoandroid.mvp.contract;

import android.content.Context;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.Query;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29 14:25
 * 描    述：
 * =============================================
 */
public interface WXEntryContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        Context getActivity();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<String> getWeixinAccessToken( String code);
    }
}
