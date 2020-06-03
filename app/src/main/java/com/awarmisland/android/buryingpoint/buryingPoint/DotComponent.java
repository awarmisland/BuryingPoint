package com.awarmisland.android.buryingpoint.buryingPoint;

import android.util.Log;
import android.view.View;

public class DotComponent {

    private static DotComponent instance;

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
