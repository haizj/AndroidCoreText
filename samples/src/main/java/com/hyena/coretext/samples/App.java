/*
 * Copyright (C) 2017 The AndroidCoreText Project
 */

package com.hyena.coretext.samples;

import android.os.Environment;

import com.hyena.coretext.builder.CYBlockProvider;
import com.hyena.coretext.samples.question.DefaultBlockBuilder;
import com.hyena.framework.clientlog.LogUtil;
import com.hyena.framework.clientlog.Logger;
import com.hyena.framework.config.FrameworkConfig;
import com.hyena.framework.network.DefaultNetworkSensor;
import com.hyena.framework.network.NetworkProvider;
import com.hyena.framework.servcie.BaseServiceManager;
import com.hyena.framework.servcie.ServiceProvider;
import com.hyena.framework.utils.BaseApp;

/**
 * Created by yangzc on 17/2/6.
 */
public class App extends BaseApp {

    @Override
    public void initApp() {
        super.initApp();
        LogUtil.setDebug(true);
        LogUtil.setLevel(Logger.DO_NOT_WRITE_LOG);
        //初始化底层服务配置
        FrameworkConfig.init(this).setAppRootDir(Environment.getExternalStorageDirectory())
                .setDebug(true);
        //注册网络服务
        NetworkProvider.getNetworkProvider().registNetworkSensor(new DefaultNetworkSensor());
        //注册应用系统服务
        ServiceProvider.getServiceProvider().registServiceManager(new ServiceManager());
        CYBlockProvider.getBlockProvider().registerBlockBuilder(new DefaultBlockBuilder());
    }

    private class ServiceManager extends BaseServiceManager {
        public ServiceManager() {
            super();
            initFrameServices();
        }
    }
}
