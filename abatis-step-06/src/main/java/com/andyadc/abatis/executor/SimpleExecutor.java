package com.andyadc.abatis.executor;

import com.andyadc.abatis.executor.statement.StatementHandler;
import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.session.Configuration;
import com.andyadc.abatis.session.ResultHandler;
import com.andyadc.abatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单执行器
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
