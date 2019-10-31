package com.echronos.epoandroid.module_cart.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.echronos.epoandroid.module_cart.BuildConfig;
import com.echronos.epoandroid.module_cart.mvp.model.api.Api;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import me.jessyan.armscomponent.commonsdk.base.app.MyBaseApplication;
import me.jessyan.armscomponent.commonsdk.core.Constants;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        DataHelper.setStringSF(application, Constants.SP_TOKEN,"37eef8f6-ffc2-4d8d-b272-b7fbb9c228d6");
        //使用 RetrofitUrlManager 切换 BaseUrl
        RetrofitUrlManager.getInstance().putDomain(Api.CART_DOMAIN_NAME, Api.CART_DOMAIN);

        //当所有模块集成到宿主 App 时, 在 App 中已经执行了以下代码
        if (BuildConfig.IS_BUILD_MODULE) {
            //leakCanary内存泄露检查
            ArmsUtils.obtainAppComponentFromContext(application).extras()
                    .put(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName())
                            , BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        }
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
