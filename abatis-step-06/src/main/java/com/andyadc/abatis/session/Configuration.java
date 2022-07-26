package com.andyadc.abatis.session;

import com.andyadc.abatis.binding.MapperRegistry;
import com.andyadc.abatis.datasource.druid.DruidDataSourceFactory;
import com.andyadc.abatis.datasource.pooled.PooledDataSourceFactory;
import com.andyadc.abatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.andyadc.abatis.executor.Executor;
import com.andyadc.abatis.executor.SimpleExecutor;
import com.andyadc.abatis.executor.resultset.DefaultResultSetHandler;
import com.andyadc.abatis.executor.resultset.ResultSetHandler;
import com.andyadc.abatis.executor.statement.PreparedStatementHandler;
import com.andyadc.abatis.executor.statement.StatementHandler;
import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.mapping.Environment;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.transaction.Transaction;
import com.andyadc.abatis.transaction.jdbc.JdbcTransactionFactory;
import com.andyadc.abatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置项
 */
public class Configuration {

    // 映射的语句，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    //环境
    protected Environment environment;
    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }
}
