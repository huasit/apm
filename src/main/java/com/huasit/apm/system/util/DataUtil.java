package com.huasit.apm.system.util;

public class DataUtil {

    public static boolean stringIsEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
