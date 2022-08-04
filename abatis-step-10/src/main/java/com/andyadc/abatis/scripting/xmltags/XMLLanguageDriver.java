package com.andyadc.abatis.scripting.xmltags;

import com.andyadc.abatis.executor.parameter.ParameterHandler;
import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.mapping.SqlSource;
import com.andyadc.abatis.scripting.LanguageDriver;
import com.andyadc.abatis.scripting.defaults.DefaultParameterHandler;
import com.andyadc.abatis.session.Configuration;
import org.dom4j.Element;

/**
 * XML语言驱动器
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }
}
