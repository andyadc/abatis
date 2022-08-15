package com.andyadc.abatis.spring;

import org.springframework.beans.factory.FactoryBean;

/**
 * Mapper 工厂对象
 *
 * @param <T>
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {

    @Override
    public T getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
