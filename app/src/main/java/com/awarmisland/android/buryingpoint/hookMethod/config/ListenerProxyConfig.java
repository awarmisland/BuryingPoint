package com.awarmisland.android.buryingpoint.hookMethod.config;

import com.awarmisland.android.buryingpoint.MainActivity;
import com.awarmisland.android.buryingpoint.R;
import com.awarmisland.android.buryingpoint.hookMethod.bean.ViewProxyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListenerProxyConfig {

    private Map<String, List<ViewProxyEvent>> configList;
    private static ListenerProxyConfig instance;



    private ListenerProxyConfig(){
    }


    public static ListenerProxyConfig getInstance(){
        if(instance==null){
            synchronized(ListenerProxyConfig.class){
                if(instance==null){
                    instance = new ListenerProxyConfig();
                }
            }
        }
        return instance;
    }

    public void initConfig(){
        if(configList==null){
            configList = new HashMap<>();
        }
        //MainActivity
        configList.put(MainActivity.class.getName(), new ArrayList<ViewProxyEvent>(){{
            add(new ViewProxyEvent(MainActivity.class.getName(), R.id.btn_start,"btn_start",ListenerProxyEnum.CLICK_PROXY));
        }});
    }

    public Map<String, List<ViewProxyEvent>> getConfigList() {
        return configList;
    }
}
