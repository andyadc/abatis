package com.andyadc.abatis.session;

import com.andyadc.abatis.binding.MapperRegistry;
import com.andyadc.abatis.datasource.druid.DruidDataSourceFactory;
import com.andyadc.abatis.datasource.pooled.PooledDataSourceFactory;
import com.andyadc.abatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.andyadc.abatis.executor.Executor;
import com.andyadc.abatis.executor.SimpleExecutor;
import com.andyadc.abatis.executor.keygen.KeyGenerator;
import com.andyadc.abatis.executor.parameter.ParameterHandler;
import com.andyadc.abatis.executor.resultset.DefaultResultSetHandler;
import com.andyadc.abatis.executor.resultset.ResultSetHandler;
import com.andyadc.abatis.executor.statement.PreparedStatementHandler;
import com.andyadc.abatis.executor.statement.StatementHandler;
import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.mapping.Environment;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.mapping.ResultMap;
import com.andyadc.abatis.plugin.Interceptor;
import com.andyadc.abatis.plugin.InterceptorChain;
import com.andyadc.abatis.reflection.MetaObject;
import com.andyadc.abatis.reflection.factory.DefaultObjectFactory;
import com.andyadc.abatis.reflection.factory.ObjectFactory;
import com.andyadc.abatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.andyadc.abatis.reflection.wrapper.ObjectWrapperFactory;
import com.andyadc.abatis.scripting.LanguageDriver;
import com.andyadc.abatis.scripting.LanguageDriverRegistry;
import com.andyadc.abatis.scripting.xmltags.XMLLanguageDriver;
import com.andyadc.abatis.transaction.Transaction;
import com.andyadc.abatis.transaction.jdbc.JdbcTransactionFactory;
import com.andyadc.abatis.type.TypeAliasRegistry;
import com.andyadc.abatis.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 配置项
 */
public class Configuration {

    // 映射的语句，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    // 结果映射，存在Map里
    protected final Map<String, ResultMap> resultMaps = new HashMap<>();
    protected final Map<String, KeyGenerator> keyGenerators = new HashMap<>();
    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();
    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
    // 插件拦截器链
    protected final InterceptorChain interceptorChain = new InterceptorChain();
    protected final Set<String> loadedResources = new HashSet<>();
    //环境
    protected Environment environment;
    protected boolean useGeneratedKeys = false;
    // 缓存机制，默认不配置的情况是 SESSION
    protected LocalCacheScope localCacheScope = LocalCacheScope.SESSION;
    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
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

    public String getDatabaseId() {
        return databaseId;
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, resultHandler, rowBounds, boundSql);
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
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        // 创建语句处理器，Mybatis 这里加了路由 STATEMENT、PREPARED、CALLABLE 我们默认只根据预处理进行实例化
        StatementHandler statementHandler = new PreparedStatementHandler(executor, mappedStatement, parameter, rowBounds, resultHandler, boundSql);
        // 嵌入插件，代理对象
        statementHandler = (StatementHandler) interceptorChain.pluginAll(statementHandler);
        return statementHandler;
    }

    // 创建元对象
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    // 类型处理器注册机
    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        // 创建参数处理器
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        // 插件的一些参数，也是在这里处理，暂时不添加这部分内容 interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public LanguageDriver getDefaultScriptingLanguageInstance() {
        return languageRegistry.getDefaultDriver();
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public ResultMap getResultMap(String id) {
        return resultMaps.get(id);
    }

    public void addResultMap(ResultMap resultMap) {
        resultMaps.put(resultMap.getId(), resultMap);
    }

    public void addKeyGenerator(String id, KeyGenerator keyGenerator) {
        keyGenerators.put(id, keyGenerator);
    }

    public KeyGenerator getKeyGenerator(String id) {
        return keyGenerators.get(id);
    }

    public boolean hasKeyGenerator(String id) {
        return keyGenerators.containsKey(id);
    }

    public boolean isUseGeneratedKeys() {
        return useGeneratedKeys;
    }

    public void setUseGeneratedKeys(boolean useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
    }

    public void addInterceptor(Interceptor interceptorInstance) {
        interceptorChain.addInterceptor(interceptorInstance);
    }

    public LocalCacheScope getLocalCacheScope() {
        return localCacheScope;
    }

    public void setLocalCacheScope(LocalCacheScope localCacheScope) {
        this.localCacheScope = localCacheScope;
    }
}
