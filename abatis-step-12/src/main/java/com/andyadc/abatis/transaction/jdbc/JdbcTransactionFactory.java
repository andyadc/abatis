package com.andyadc.abatis.transaction.jdbc;

import com.andyadc.abatis.session.TransactionIsolationLevel;
import com.andyadc.abatis.transaction.Transaction;
import com.andyadc.abatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * JdbcTransaction 工厂
 */
public class JdbcTransactionFactory implements TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     */
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    /**
     * 根据数据源和事务隔离级别创建 Transaction
     */
    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
