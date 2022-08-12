package com.andyadc.abatis.executor.keygen;

import com.andyadc.abatis.executor.Executor;
import com.andyadc.abatis.mapping.MappedStatement;

import java.sql.Statement;

/**
 * 不用键值生成器
 */
public class NoKeyGenerator implements KeyGenerator {

    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // do Nothing
    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // do Nothing
    }
}
