package com.awarmisland.android.buryingpoint.hookMethod;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


/**
 * @author ljz
 */
public class HookViewManager {
    private Context context;
    private static HookViewBuilder builder;

    public static HookViewBuilder builder(){
        builder = new HookViewBuilder();
        return builder;
    }

    public static HookViewBuilder builder(Activity activity){
        builder = new HookViewBuilder(activity);
        return builder;
    }

    public static HookViewBuilder builder(Fragment fragment){
        builder = new HookViewBuilder(fragment);
        return builder;
    }


}
