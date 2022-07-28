package com.andyadc.abatis.scripting;

import com.andyadc.abatis.mapping.SqlSource;
import com.andyadc.abatis.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);
}
