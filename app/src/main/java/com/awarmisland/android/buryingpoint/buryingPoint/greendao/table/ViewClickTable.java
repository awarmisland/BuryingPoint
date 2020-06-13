package com.awarmisland.android.buryingpoint.buryingPoint.greendao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ViewClickTable {
    @Id(autoincrement = true)
    private Long id;

    private String className;

    private String view_name;

    private long time;

    @Generated(hash = 1240804539)
    public ViewClickTable(Long id, String className, String view_name, long time) {
        this.id = id;
        this.className = className;
        this.view_name = view_name;
        this.time = time;
    }

    @Generated(hash = 2001035)
    public ViewClickTable() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getView_name() {
        return this.view_name;
    }

    public void setView_name(String view_name) {
        this.view_name = view_name;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
