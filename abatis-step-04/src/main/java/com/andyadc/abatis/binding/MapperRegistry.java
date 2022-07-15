package com.andyadc.abatis.binding;

import com.andyadc.abatis.session.Configuration;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private Configuration config;

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    /**
     * 将已添加的映射器代理加入到 HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

}
