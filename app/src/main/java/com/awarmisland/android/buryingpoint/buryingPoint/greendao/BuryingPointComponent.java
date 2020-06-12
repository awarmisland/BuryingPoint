package com.awarmisland.android.buryingpoint.buryingPoint.greendao;

import android.content.Context;

import java.util.List;

public class BuryingPointComponent {

    private ViewLifecycleTableDao viewLifecycleTableDao;

    public BuryingPointComponent(Context context){
        init(context);
    }

    public void init(Context context){
        viewLifecycleTableDao = DBHelper.getInstance(context)
                .getDaoSession().getViewLifecycleTableDao();
    }


    public List<ViewLifecycleTable> queryViewLifecycle(){
       return viewLifecycleTableDao.queryBuilder().list();
    }
    public void insertViewTable(ViewLifecycleTable table){
        viewLifecycleTableDao.insert(table);
    }


}
