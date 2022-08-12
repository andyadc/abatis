package com.andyadc.abatis.test.plugin;

import com.andyadc.abatis.executor.statement.StatementHandler;
import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.plugin.Interceptor;
import com.andyadc.abatis.plugin.Intercepts;
import com.andyadc.abatis.plugin.Invocation;
import com.andyadc.abatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

@Intercepts(
        {
                @Signature(
                        type = StatementHandler.class,
                        method = "prepare",
                        args = {
                                Connection.class
                        }
                )
        }
)
public class TestPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取StatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 获取SQL信息
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        // 输出SQL
        System.out.println("拦截SQL：" + sql);
        // 放行
        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("参数输出：" + properties.getProperty("test00"));
    }
}
