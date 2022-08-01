package com.andyadc.abatis.util;

import com.alibaba.fastjson2.JSON;

public final class Utils {

    public static String toJSONString(Object o) {
        return JSON.toJSONString(o);
    }
}
