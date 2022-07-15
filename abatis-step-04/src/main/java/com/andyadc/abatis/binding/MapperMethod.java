package com.andyadc.abatis.binding;

import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.session.Configuration;

import java.lang.reflect.Method;

/**
 * 映射器方法
 */
public class MapperMethod {

    private final SqlCommand command;

    /**
     * SQL 指令
     */
    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
