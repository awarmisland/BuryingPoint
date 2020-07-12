package com.awarmisland.android.buryingpoint.hookMethod.bean;


import com.awarmisland.android.buryingpoint.hookMethod.config.ListenerProxyEnum;

/**
 * 自定义view 事件
 */
public class ViewProxyEvent {
    //view所在页面
    public String className;
    //viewId;
    public int viewId;
    //view name
    public String viewIdName;
    //代理事件
    public ListenerProxyEnum proxyEnum;


    public ViewProxyEvent(String className, int viewId, String viewIdName, ListenerProxyEnum proxyEnum){
        this.className = className;
        this.viewId = viewId;
        this.viewIdName = viewIdName;
        this.proxyEnum = proxyEnum;
    }
}
