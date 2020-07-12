package com.awarmisland.android.buryingpoint.hookMethod.config;


import com.awarmisland.android.buryingpoint.hookMethod.proxy.OnClickListenerProxy;

public enum ListenerProxyEnum {

    CLICK_PROXY("mOnClickListener", OnClickListenerProxy.class);


    public String listenName;
    public Class cls;

    ListenerProxyEnum(String listenName, Class clsName){
         this.listenName = listenName;
         this.cls=clsName;
    }

}
