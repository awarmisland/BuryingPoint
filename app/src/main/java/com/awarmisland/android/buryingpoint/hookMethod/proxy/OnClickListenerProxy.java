package com.awarmisland.android.buryingpoint.hookMethod.proxy;

import android.util.Log;
import android.view.View;
import com.awarmisland.android.buryingpoint.CusApplication;
import com.awarmisland.android.buryingpoint.buryingPoint.DotComponent;
import com.awarmisland.android.buryingpoint.hookMethod.bean.ViewProxyEvent;


/**
 *  代理点击事件
 */
public class OnClickListenerProxy extends BaseListenerProxy<View.OnClickListener> implements View.OnClickListener {

    private int MIN_CLICK_DELAY_TIME = 1000;

    private long lastClickTime = 0;

    //代理点击事件
    private ViewProxyEvent event;

    public OnClickListenerProxy(View.OnClickListener object, ViewProxyEvent event) {
        this.object = object;
        this.event = event;
    }


    @Override
    public void onClick(View v) {
        //点击时间控制
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            Log.d("DotCom","page: "+ event.className+ "id: "+event.viewId+" event: "+ event.proxyEnum.listenName);
            //执行注入事件
            execute();
            if(object != null) {
                object.onClick(v);
            }
        }
    }


    @Override
    protected void execute() {
        DotComponent.getInstance().recordViewClick(event.className, event.viewIdName);
    }
}
