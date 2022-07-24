package com.andyadc.abatis.test;

import com.andyadc.abatis.datasource.pooled.PooledDataSource;
import com.andyadc.abatis.io.Resources;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.session.SqlSessionFactory;
import com.andyadc.abatis.session.SqlSessionFactoryBuilder;
import com.andyadc.abatis.test.entity.User;
import com.andyadc.abatis.test.mapper.UserMapper;
import com.andyadc.abatis.util.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class Tests {

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从 SqlSessionFactory中获取 SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 3. 测试验证
        for (int i = 0; i < 50; i++) {
            User user = userMapper.selectById(1000L);
            System.out.println(Utils.toJson(user));
        }
    }

    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/abatis?useUnicode=true");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("andyadc");
        // 持续获得链接
        while (true) {
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
        }
    }
}
