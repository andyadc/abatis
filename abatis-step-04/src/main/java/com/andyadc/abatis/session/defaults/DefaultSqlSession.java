package com.andyadc.abatis.session.defaults;

import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.mapping.Environment;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.session.Configuration;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.util.Utils;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 默认SqlSession实现类
 */
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
        try {
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            Environment environment = configuration.getEnvironment();

            Connection connection = environment.getDataSource().getConnection();

            BoundSql boundSql = mappedStatement.getBoundSql();

            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1, Long.parseLong(((Object[]) parameter)[0].toString()));
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objList = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
            if (objList.size() > 0)
                return objList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    if (value == null) {
                        continue;
                    }
                    if (value instanceof BigInteger) {
                        value = ((BigInteger) value).longValue();
                    }

                    String columnName = metaData.getColumnName(i);

                    String property = Utils.capitalize(Utils.underline2CamelCase(columnName));
                    String setMethod = "set" + property;
                    Method method;
                    if (value instanceof java.sql.Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else if (value instanceof java.sql.Date) {
                        method = clazz.getMethod(setMethod, LocalDate.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
