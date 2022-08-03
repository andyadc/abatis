package com.andyadc.abatis.scripting;

import com.andyadc.abatis.executor.parameter.ParameterHandler;
import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.mapping.SqlSource;
import com.andyadc.abatis.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动
 */
public interface LanguageDriver {

    /**
     * 创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);
}
