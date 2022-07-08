package com.andyadc.abatis.test;

import com.andyadc.abatis.binding.MapperRegistry;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.session.SqlSessionFactory;
import com.andyadc.abatis.session.defaults.DefaultSqlSessionFactory;
import com.andyadc.abatis.test.mapper.UserMapper;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void test_MapperProxyFactory() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.andyadc.abatis.test.mapper");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 4. 测试验证
        String result = userMapper.queryUserName(10001L);

        System.out.println(result);
    }
}