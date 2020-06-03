package com.awarmisland.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())||"null".equals(str.trim().toLowerCase())) {
            return true;
        }

        return false;
    }

}
