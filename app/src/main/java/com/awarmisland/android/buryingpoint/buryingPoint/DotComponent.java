package com.awarmisland.android.buryingpoint.buryingPoint;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.awarmisland.android.buryingpoint.CusApplication;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.DBComponent;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.table.RecordMethodTable;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.table.ViewClickTable;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.table.ViewLifecycleTable;
import com.awarmisland.android.buryingpoint.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

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
        Log.d("DotCom",className+" "+lifecycle+" time: "+time);
        if(context!=null){
            ViewLifecycleTable table = new ViewLifecycleTable();
            table.setClassName(className);
            table.setLifecycle(lifecycle);
            table.setTime(time);
            DBComponent component = new DBComponent(context);
            component.insertViewTable(table);
        }
    }

    public void recordViewClick(String className,View view){
        CharSequence name= view.getResources().getResourceEntryName(view.getId());
        long time = System.currentTimeMillis();
        Log.d("DotCom",name +" "+" time: "+time);
        if(context!=null){
            ViewClickTable table = new ViewClickTable();
            table.setTime(time);
            table.setClassName(className);
            table.setView_name(name.toString());
            DBComponent component = new DBComponent(context);
            component.insertViewClickTable(table);
        }
    }

    public void recordMethods(String className, String method, String args){
         recordMethods(className,method,new ArrayList<String>(){{add(args);}});
    }

    public void recordMethods(String className, String method, List<String> args){
        long time = System.currentTimeMillis();
        Log.d("DotCom",className+"Method："+method+" args："+args+" time: "+time);
        if(context!=null&&!ListUtils.isEmptyList(args)){
            List<RecordMethodTable> insertTables = new ArrayList<>();
            for(String arg : args){
                RecordMethodTable table = new RecordMethodTable();
                table.setClassName(className);
                table.setMethod(method);
                table.setArgs(arg);
                table.setTime(time);
                insertTables.add(table);
            }
            DBComponent component = new DBComponent(context);
            component.insertRecordMethodTable(insertTables);
        }
    }
}
