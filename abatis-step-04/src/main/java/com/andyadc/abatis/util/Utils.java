package com.andyadc.abatis.util;

import com.google.gson.Gson;

public final class Utils {

    public static String toJson(Object o) {
        return new Gson().toJson(o);
    }
}
