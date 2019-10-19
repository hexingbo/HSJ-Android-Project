package com.echronos.epoandroid.hsjapp.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.echronos.epoandroid.hsjapp.di.module.MainStartModule;
import com.echronos.epoandroid.hsjapp.mvp.contract.MainStartContract;

import com.jess.arms.di.scope.ActivityScope;
import com.echronos.epoandroid.hsjapp.mvp.ui.activity.MainStartActivity;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/02 16:30
 * 描    述：
 * =============================================
 */
@ActivityScope
@Component(modules = MainStartModule.class, dependencies = AppComponent.class)
public interface MainStartComponent {
    void inject(MainStartActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainStartComponent.Builder view(MainStartContract.View view);

        MainStartComponent.Builder appComponent(AppComponent appComponent);

        MainStartComponent build();
    }
}