package com.awarmisland.android.buryingpoint.hookMethod;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.awarmisland.android.buryingpoint.hookMethod.bean.ViewProxyEvent;
import com.awarmisland.android.buryingpoint.hookMethod.config.ListenerProxyConfig;
import com.awarmisland.android.buryingpoint.hookMethod.config.ListenerProxyEnum;
import com.awarmisland.android.buryingpoint.hookMethod.proxy.BaseListenerProxy;
import com.awarmisland.android.buryingpoint.hookMethod.proxy.OnClickListenerProxy;
import com.awarmisland.android.buryingpoint.utils.ListUtils;


public class HookViewBuilder {

    private Map<String, List<ViewProxyEvent>> eventMap;
    private List<ViewProxyEvent>  eventList;
    private Activity activity;
    private Fragment fragment;


    public HookViewBuilder(){
    }


    public HookViewBuilder(Activity activity){
        this.activity = activity;
        initData(activity);
    }

    public HookViewBuilder(Fragment fragment){
        this.fragment = fragment;
        initData(fragment);
    }


    public void proxyViewEvent(){
        if(!ListUtils.isEmptyList(eventList)){
            for(ViewProxyEvent event:eventList){
                setViewEvent(event);
            }
        }
    }

    public void iniConfig() {
        ListenerProxyConfig.getInstance().initConfig();
    }

    private void initData(Object targetPage){
        eventList = new ArrayList<>();
        eventMap = ListenerProxyConfig.getInstance().getConfigList();
        if(eventMap ==null){
            ListenerProxyConfig.getInstance().initConfig();
            eventMap = ListenerProxyConfig.getInstance().getConfigList();
        }

        String cls = targetPage.getClass().getName();
        if (eventMap != null) {
            List<ViewProxyEvent> eventList = eventMap.get(cls);
            if(!ListUtils.isEmptyList(eventList)){
                this.eventList.addAll(eventList);
            }
        }
    }



    private void setViewEvent(final ViewProxyEvent event){
        if(activity!=null){
            View view = activity.findViewById(event.viewId);
            hookView(view,event);
        }
        if(fragment!=null){
            final View view = fragment.getView().findViewById(event.viewId);
            if(view!=null){
                hookView(view,event);
            }else{
            }

        }
    }

    private  void hookView(View view,ViewProxyEvent event){
        try {
            ListenerProxyEnum proxyEnum = event.proxyEnum;
            Class viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);

            Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

            Field onClickListenerField = listenerInfoClazz.getDeclaredField(proxyEnum.listenName);

            if (!onClickListenerField.isAccessible()) {
                onClickListenerField.setAccessible(true);
            }
            Object mListener =   onClickListenerField.get(listenerInfoObj);

            //自定义代理事件监听器
            BaseListenerProxy proxy = getProxyInstance(proxyEnum.cls,mListener,event);
//            View.OnClickListener onClickListenerProxy = new OnClickListenerProxy(mOnClickListener);
            //更换
            onClickListenerField.set(listenerInfoObj, proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseListenerProxy getProxyInstance(Class proxyClass, Object sourceEvent,ViewProxyEvent event){
        if(proxyClass.getSimpleName().equals(OnClickListenerProxy.class.getSimpleName())){
            return new OnClickListenerProxy((View.OnClickListener) sourceEvent,event);
        }
        return null;
    }


}
