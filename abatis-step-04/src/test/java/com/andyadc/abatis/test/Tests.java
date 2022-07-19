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

public class Tests {

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByUserId(1L);

        System.out.println(Utils.toJson(user));
    }

    @Test
    public void test001() {
        System.out.println(Utils.underline2CamelCase("user_login_id"));
        System.out.println(Utils.underline2CamelCase("name"));
        System.out.println(Utils.camelCase2Underline("Name"));
        System.out.println(Utils.camelCase2Underline("UserName"));
        System.out.println(Utils.camelCase2Underline("userName"));
    }
}
