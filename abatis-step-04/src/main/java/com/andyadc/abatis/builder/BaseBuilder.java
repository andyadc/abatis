package com.andyadc.abatis.builder;

import com.andyadc.abatis.session.Configuration;
import com.andyadc.abatis.type.TypeAliasRegistry;

/**
 * 构建器的基类，建造者模式
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
