package com.echronos.epoandroid.hsjapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.echronos.epoandroid.hsjapp.R;
import com.echronos.epoandroid.hsjapp.di.component.DaggerMainStartComponent;
import com.echronos.epoandroid.hsjapp.mvp.contract.MainStartContract;
import com.echronos.epoandroid.hsjapp.mvp.presenter.MainStartPresenter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.commonservice.me.bean.MyFragmentView;
import me.jessyan.armscomponent.commonservice.me.service.MyFragmentViewService;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/02 16:30
 * 描    述：
 * =============================================
 */
@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainStartActivity extends BaseActivity<MainStartPresenter> implements MainStartContract.View {

    @Autowired(name = RouterHub.Me_Service_MyFragmentViewService)
    MyFragmentViewService mMyFragmentViewService;

    @BindView(R.id.fl_main_app)
    FrameLayout flMainApp;
    @BindView(R.id.main_table)
    CommonTabLayout mainTable;

    private int selectIndex = 0;
    private long mPressedTime;
    Fragment mCurrentFragment;
    private Fragment myFragment;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainStartComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main_start; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        ArmsUtils.statuInScreen(this);
        loadMyFragmentView();
        ArrayList<CustomTabEntity> mTabEntities = mPresenter.getTabEntity();
        mainTable.setTabData(mTabEntities);
        mainTable.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == selectIndex) {
                    return;
                }
                selectIndex = position;
                switch (position) {
                    case 0:
//                        selectedMainShopFragment();
                        break;
                    case 1:
//                        selectedMainBusinessFragment();
                        break;
                    case 2:
                        mainTable.showMsg(2, 10);//设置红点
//                        selectedMainCartFragment();
                        break;
                    case 3:
//                        selectedMainMyFragment();
                        break;
                    default:
//                        selectedMainShopFragment();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
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

    private void loadMyFragmentView() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
        if (mMyFragmentViewService == null) {
            return;
        }
        MyFragmentView myFragmentView = mMyFragmentViewService.getMyFragmentView();
        myFragment = myFragmentView.getMyFragment();
        mCurrentFragment = myFragment;
        initDefaultFragment(myFragment);
    }

    /**
     * 初始化第一个默认的fragment
     */
    protected void initDefaultFragment(Fragment fragment) {
        //开启一个事务
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //add：往碎片集合中添加一个碎片；
        //replace：移除之前所有的碎片，替换新的碎片（remove和add的集合体）(很少用，不推荐，因为是重新加载，所以消耗流量)
        //参数：1.公共父容器的的id  2.fragment的碎片
        fragmentTransaction.add(R.id.fl_main_app, fragment);
        fragmentTransaction.addToBackStack(null);

        //提交事务
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        //获取第一次按键时间
        long mNowTime = System.currentTimeMillis();
        //比较两次按键时间差
        if ((mNowTime - mPressedTime) > 2000) {
            ArmsUtils.makeText(getApplicationContext(),
                    "再按一次退出" + ArmsUtils.getString(getApplicationContext(), R.string.public_app_name));
            mPressedTime = mNowTime;
        } else {
//            super.onBackPressed();
            AppManager.getAppManager().appExit();
        }
    }
}
