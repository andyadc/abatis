package com.andyadc.abatis.session;

import com.andyadc.abatis.binding.MapperRegistry;
import com.andyadc.abatis.mapping.Environment;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    //环境
    protected Environment environment;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

}
