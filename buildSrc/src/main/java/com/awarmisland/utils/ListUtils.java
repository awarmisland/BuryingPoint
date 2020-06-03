package com.awarmisland.utils;

import java.util.List;

public class ListUtils {

    public static boolean isEmptyList(List list){
        boolean flag = true;
        if(list!=null&& list.size()>0){
            flag=false;
        }
        return flag;
    }
}
