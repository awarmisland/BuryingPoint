package com.awarmisland.android.buryingpoint.buryingPoint.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper {
    private static DBHelper mInstance;
    private Context context;
    private final static String db_name="buryingPoint.db";
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    /**
     * 获取单例
     */
    public static DBHelper getInstance(Context context){
        if(mInstance == null){
            synchronized (DBHelper.class){
                if(mInstance == null){
                    mInstance = new DBHelper(context);
                }
            }
        }
        return mInstance;
    }

    private DBHelper(Context context){
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context,db_name);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }



    private SQLiteDatabase getReadableDatabase(){
        if(mHelper==null){
            mHelper = new DaoMaster.DevOpenHelper(context,db_name,null);
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db;
    }

    private SQLiteDatabase getWritableDatabase(){
        if(mHelper==null){
            mHelper = new DaoMaster.DevOpenHelper(context,db_name,null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
