package com.andyadc.abatis.mapping;

import com.andyadc.abatis.executor.keygen.Jdbc3KeyGenerator;
import com.andyadc.abatis.executor.keygen.KeyGenerator;
import com.andyadc.abatis.executor.keygen.NoKeyGenerator;
import com.andyadc.abatis.scripting.LanguageDriver;
import com.andyadc.abatis.session.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * 映射语句类
 */
public class MappedStatement {

    Class<?> resultType;
    private String resource;
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;
    private SqlSource sqlSource;
    private LanguageDriver lang;
    private List<ResultMap> resultMaps;

    private KeyGenerator keyGenerator;
    private String[] keyProperties;
    private String[] keyColumns;

    private boolean flushCacheRequired;

    MappedStatement() {
        // constructor disabled
    }

    private static String[] delimitedStringToArray(String in) {
        if (in == null || in.trim().length() == 0) {
            return null;
        } else {
            return in.split(",");
        }
    }

    public BoundSql getBoundSql(Object parameterObject) {
        // 调用 SqlSource#getBoundSql
        return sqlSource.getBoundSql(parameterObject);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public LanguageDriver getLang() {
        return lang;
    }

    public List<ResultMap> getResultMaps() {
        return resultMaps;
    }

    public String[] getKeyColumns() {
        return keyColumns;
    }

    public String[] getKeyProperties() {
        return keyProperties;
    }

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }

    public String getResource() {
        return resource;
    }

    public boolean isFlushCacheRequired() {
        return flushCacheRequired;
    }

    /**
     * 建造者
     */
    public static class Builder {

        private final MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, SqlSource sqlSource, Class<?> resultType) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.sqlSource = sqlSource;
            mappedStatement.resultType = resultType;
            mappedStatement.keyGenerator = configuration.isUseGeneratedKeys() && SqlCommandType.INSERT.equals(sqlCommandType) ? new Jdbc3KeyGenerator() : new NoKeyGenerator();
            mappedStatement.lang = configuration.getDefaultScriptingLanguageInstance();
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            mappedStatement.resultMaps = Collections.unmodifiableList(mappedStatement.resultMaps);
            return mappedStatement;
        }

        public Builder resource(String resource) {
            mappedStatement.resource = resource;
            return this;
        }

        public String id() {
            return mappedStatement.id;
        }

        public Builder resultMaps(List<ResultMap> resultMaps) {
            mappedStatement.resultMaps = resultMaps;
            return this;
        }

        public Builder keyGenerator(KeyGenerator keyGenerator) {
            mappedStatement.keyGenerator = keyGenerator;
            return this;
        }

        public Builder keyProperty(String keyProperty) {
            mappedStatement.keyProperties = delimitedStringToArray(keyProperty);
            return this;
        }

    }
}
