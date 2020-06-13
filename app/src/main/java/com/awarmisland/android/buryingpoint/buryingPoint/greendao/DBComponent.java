package com.awarmisland.android.buryingpoint.buryingPoint.greendao;

import android.content.Context;


import com.awarmisland.android.buryingpoint.buryingPoint.greendao.table.ViewClickTable;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.table.ViewClickTableDao;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.table.ViewLifecycleTable;
import com.awarmisland.android.buryingpoint.buryingPoint.greendao.table.ViewLifecycleTableDao;

import java.util.List;

public class DBComponent {

    private ViewLifecycleTableDao viewLifecycleTableDao;
    private ViewClickTableDao viewClickTableDao;

    public DBComponent(Context context){
        init(context);
    }

    public void init(Context context){
        viewLifecycleTableDao = DBHelper.getInstance(context)
                .getDaoSession().getViewLifecycleTableDao();
        viewClickTableDao = DBHelper.getInstance(context)
                .getDaoSession().getViewClickTableDao();
    }


    public List<ViewLifecycleTable> queryViewLifecycle(){
       return viewLifecycleTableDao.queryBuilder().list();
    }


    public void insertViewTable(ViewLifecycleTable table){
        viewLifecycleTableDao.insert(table);
    }

    public void insertViewClickTable(ViewClickTable table){
        viewClickTableDao.insert(table);
    }

}
