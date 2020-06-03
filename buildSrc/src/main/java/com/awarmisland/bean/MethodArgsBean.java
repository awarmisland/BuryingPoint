package com.awarmisland.bean;

/**
 *  方法参数 bean
 */
public class MethodArgsBean {
    private int index;
    private String argName;

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getArgName() {
        return argName;
    }
}
