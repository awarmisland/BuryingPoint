package com.awarmisland.android.buryingpoint.buryingPoint.greendao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RecordMethodTable {
    @Id(autoincrement = true)
    private Long id;

    private String className;

    private String method;

    private String args;

    private long time;

    @Generated(hash = 1468332190)
    public RecordMethodTable(Long id, String className, String method, String args,
            long time) {
        this.id = id;
        this.className = className;
        this.method = method;
        this.args = args;
        this.time = time;
    }

    @Generated(hash = 951884894)
    public RecordMethodTable() {
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

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArgs() {
        return this.args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
