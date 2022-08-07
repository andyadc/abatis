package com.andyadc.abatis.test;

import com.andyadc.abatis.io.Resources;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.session.SqlSessionFactory;
import com.andyadc.abatis.session.SqlSessionFactoryBuilder;
import com.andyadc.abatis.test.entity.User;
import com.andyadc.abatis.test.mapper.UserMapper;
import com.andyadc.abatis.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Reader;

public class Tests {

    private SqlSession sqlSession;

    @BeforeEach
    public void init() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_12_UserMapper_selectById() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectById(1000L);
        System.out.println(Utils.toJSONString(user));
    }
}
