package com.andyadc.abatis.util;

import com.alibaba.fastjson2.JSON;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {

    public static String toJSONString(Object o) {
        return JSON.toJSONString(o);
    }

    public static String capitalize(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }
        return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
    }

    public static String camelCase2Underline(String str) {
        Pattern compile = Pattern.compile("[A-Z]");
        Matcher matcher = compile.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String underline2CamelCase(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }
        str = str.toLowerCase();
        Pattern compile = Pattern.compile("_[a-z]");
        Matcher matcher = compile.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(0).toUpperCase().replace("_", ""));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
