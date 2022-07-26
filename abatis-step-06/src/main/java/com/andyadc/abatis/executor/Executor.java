package com.andyadc.abatis.executor;

import com.andyadc.abatis.mapping.BoundSql;
import com.andyadc.abatis.mapping.MappedStatement;
import com.andyadc.abatis.session.ResultHandler;
import com.andyadc.abatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
