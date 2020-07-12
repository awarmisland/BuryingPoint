package com.awarmisland.android.buryingpoint.hookMethod.proxy;


public  abstract class BaseListenerProxy<T> {
    protected T object;

    protected void BaseListenerProxy(T t){
        this.object = t;
    }

    protected abstract void execute();
}
