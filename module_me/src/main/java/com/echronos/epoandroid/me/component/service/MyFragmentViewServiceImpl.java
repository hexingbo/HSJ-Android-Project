/*
 * Copyright 2018 JessYan
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
package com.echronos.epoandroid.me.component.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.echronos.epoandroid.me.mvp.ui.fragment.MainMyFragment;

import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.commonservice.me.bean.MyFragmentView;
import me.jessyan.armscomponent.commonservice.me.service.MyFragmentViewService;


/**
 * ================================================
 * 向外提供服务的接口实现类, 在此类中实现一些具有特定功能的方法提供给外部, 即可让一个组件与其他组件或宿主进行交互
 * ================================================
 */
@Route(path = RouterHub.Me_Service_MyFragmentViewService, name = "我的页面Fragment")
public class MyFragmentViewServiceImpl implements MyFragmentViewService {
    private Context mContext;

    @Override
    public MyFragmentView getMyFragmentView() {
        return new MyFragmentView(new MainMyFragment());
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
