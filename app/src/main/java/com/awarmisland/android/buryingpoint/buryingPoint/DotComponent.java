package com.awarmisland.android.buryingpoint.buryingPoint;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.awarmisland.android.buryingpoint.CusApplication;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.BuryingPointComponent;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.ViewLifecycleTable;

public class DotComponent {

    private static DotComponent instance;
    private Context context;

    private DotComponent(){
        this.context = CusApplication.application;
    }

    public static DotComponent getInstance() {
        if (instance == null) {
            synchronized (DotComponent.class) {
                if (instance == null) {
                    instance = new DotComponent();
                }
            }
        }
        return instance;
    }

    public void recordLifecycle(String className, String lifecycle){
        long time = System.currentTimeMillis();
        if(context!=null){
            ViewLifecycleTable table = new ViewLifecycleTable();
            table.setClassName(className);
            table.setLifecycle(lifecycle);
            table.setTime(time);
            BuryingPointComponent component = new BuryingPointComponent(context);
            component.insertViewTable(table);
        }
        Log.d("DotCom",className+" "+lifecycle+" time: "+time);
    }

    public void recordViewClick(View view){
        CharSequence name= view.getResources().getResourceEntryName(view.getId());
        long time = System.currentTimeMillis();
        Log.d("DotCom",name +" "+" time: "+time);
    }

    public void recordMethods(String className,String method,String args){
        long time = System.currentTimeMillis();
        Log.d("DotCom",className+"Method："+method+" args："+args+" time: "+time);
    }
}
