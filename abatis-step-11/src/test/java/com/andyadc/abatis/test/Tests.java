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
import java.util.List;

public class Tests {

    private SqlSession sqlSession;

    @BeforeEach
    public void init() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_11_UserMapper_selectById() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectById(1000L);
        System.out.println(Utils.toJSONString(user));
    }

    @Test
    public void test_11_UserMapper_selectByUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setName("adc");
        user.setStatus(1);
        user = userMapper.selectByUser(user);
        System.out.println(Utils.toJSONString(user));
    }

    @Test
    public void test_11_UserMapper_selectByStatus() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setStatus(1);
        List<User> list = userMapper.selectByStatus(user);
        System.out.println(Utils.toJSONString(list));
    }

    @Test
    public void test_11_UserMapper_updateAgeById() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setAge(123);
        user.setId(1003L);
        int result = userMapper.updateAgeById(user);
        System.out.println(result);

        sqlSession.commit();
    }

    @Test
    public void test_11_UserMapper_insertUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setName("adc1");
        user.setAge(111);
        user.setStatus(1);
        user = userMapper.insertUser(user);
        System.out.println(Utils.toJSONString(user));

        sqlSession.commit();
    }

    @Test
    public void test_11_UserMapper_deleteByName() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setName("1");
        int result = userMapper.deleteByName(user);
        System.out.println(result);

        sqlSession.commit();
    }
}
