package com.andyadc.abatis.session.defaults;

import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.session.Configuration;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.util.Utils;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" +
                "\n方法：" + statement +
                "\n入参：" + Utils.toJson(parameter) +
                "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
