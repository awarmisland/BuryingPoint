package com.awarmisland.android.buryingpoint;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.awarmisland.android.buryingpoint.buryingPoint.greendao.DBHelper;
import com.awarmisland.android.buryingpoint.hookMethod.HookViewManager;

public class CusApplication extends MultiDexApplication {

    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        DBHelper.getInstance(application);
        HookViewManager.builder().iniConfig();
    }


}
