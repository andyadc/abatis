package com.andyadc.abatis.session.defaults;

import com.andyadc.abatis.session.Configuration;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.session.SqlSessionFactory;

/**
 * 默认的 DefaultSqlSessionFactory
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return null;
    }
}
