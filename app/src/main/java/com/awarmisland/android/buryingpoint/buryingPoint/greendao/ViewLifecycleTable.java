package com.awarmisland.android.buryingpoint.buryingPoint.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ViewLifecycleTable {
    @Id(autoincrement = true)
    private long id;

    private String className;

    private String lifecycle;

    private long time;


    @Generated(hash = 1276968597)
    public ViewLifecycleTable(long id, String className, String lifecycle,
            long time) {
        this.id = id;
        this.className = className;
        this.lifecycle = lifecycle;
        this.time = time;
    }

    @Generated(hash = 601234493)
    public ViewLifecycleTable() {
    }




    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLifecycle() {
        return this.lifecycle;
    }

    public void setLifecycle(String lifecycle) {
        this.lifecycle = lifecycle;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
