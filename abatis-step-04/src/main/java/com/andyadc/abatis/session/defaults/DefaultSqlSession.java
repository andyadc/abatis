package com.andyadc.abatis.session.defaults;

import com.andyadc.abatis.session.Configuration;
import com.andyadc.abatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return null;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return null;
    }
}
