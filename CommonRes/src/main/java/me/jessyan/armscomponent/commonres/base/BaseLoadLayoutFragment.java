/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.jessyan.armscomponent.commonres.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxb.app.loadlayoutlibrary.LoadLayout;
import com.hxb.app.loadlayoutlibrary.OnLoadListener;
import com.hxb.app.loadlayoutlibrary.State;
import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.FragmentLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import me.jessyan.armscomponent.commonres.R;

/**
 * ================================================
 * 因为 Java 只能单继承, 所以如果要用到需要继承特定 @{@link Fragment} 的三方库, 那你就需要自己自定义 @{@link Fragment}
 * 继承于这个特定的 @{@link Fragment}, 然后再按照 {@link BaseLoadLayoutFragment} 的格式, 将代码复制过去, 记住一定要实现{@link IFragment}
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki">请配合官方 Wiki 文档学习本框架</a>
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki/UpdateLog">更新日志, 升级必看!</a>
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki/Issues">常见 Issues, 踩坑必看!</a>
 * @see <a href="https://github.com/JessYanCoding/ArmsComponent/wiki">MVPArms 官方组件化方案 ArmsComponent, 进阶指南!</a>
 * Created by JessYan on 22/03/2016
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public abstract class BaseLoadLayoutFragment<P extends IPresenter> extends Fragment implements IFragment, FragmentLifecycleable, OnLoadListener ,LoadlayoutViewInflater{
    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();
    RelativeLayout publicToolbarBack;
    TextView publicToolbarTitle;
    Toolbar publicToolbar;
    Unbinder unbinder;
    private Cache<String, Object> mCache;
    protected FrameLayout frameLayout;
    protected LoadLayout mLoadLayout;
    /**
     * 上下文对象
     */
    protected Context mContext;

    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(getActivity()).cacheFactory().build(CacheType.FRAGMENT_CACHE);
        }
        return mCache;
    }

    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.public_base_loadlayout, container, false);
        publicToolbarBack = rootView.findViewById(R.id.public_toolbar_back);
        publicToolbarTitle = rootView.findViewById(R.id.public_toolbar_title);
        publicToolbar = rootView.findViewById(R.id.public_toolbar);
        frameLayout = rootView.findViewById(R.id.fl_content);
        mLoadLayout = rootView.findViewById(R.id.base_load_layout);
        View contentView = initView(inflater, container, savedInstanceState);
        if (contentView != null) {
            frameLayout.addView(contentView);
        }
        publicToolbarBack.setOnClickListener(onBackClickListener);
        mLoadLayout.setOnLoadListener(this);
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true} (默认为 {@code true}), Arms 会自动注册 EventBus
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    protected void setFailedViewId(int failedViewId) {
        if (failedViewId != 0) {
            mLoadLayout.setFailedViewId(failedViewId);
        }
    }

    protected void setNoDataViewId(int noDataViewId) {
        if (noDataViewId != 0) {
            mLoadLayout.setNoDataViewId(noDataViewId);
        }
    }

    protected void setLoadingViewId(int loadingViewId) {
        if (loadingViewId != 0) {
            mLoadLayout.setLoadingViewId(loadingViewId);
        }
    }


    protected void setLayoutState_LOADING() {
        mLoadLayout.setLayoutState(State.LOADING);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //    @OnClick(R2.id.public_toolbar_back)
//    public void onViewClicked() {
//    }
    View.OnClickListener onBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().finish();
        }
    };

    public void setToolbarTitle(int res) {
        publicToolbarTitle.setText(res);
    }

    public void setToolbarTitle(String res) {
        publicToolbarTitle.setText(ArmsUtils.isEmpty(res) ? getResources().getString(R.string.app_name) : res);
    }

    @Override
    public void setLayoutState_SUCCESS() {
        mLoadLayout.setLayoutState(State.SUCCESS);
    }

    @Override
    public void setLayoutState_FAILED() {
        mLoadLayout.setLayoutState(State.FAILED);
    }

    @Override
    public void setLayoutState_NO_DATA() {
        mLoadLayout.setLayoutState(State.NO_DATA);
    }
}
