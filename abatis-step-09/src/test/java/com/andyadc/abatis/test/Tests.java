package com.andyadc.abatis.test;

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

public class Tests {

    @Test
    public void test_09_SqlSessionFactory() throws IOException {
        // 1. 从 SqlSessionFactory中获取 SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 3. 测试验证
        User user = userMapper.selectById(1000L);
        System.out.println(Utils.toJSONString(user));
    }
}
