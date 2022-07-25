package com.andyadc.abatis.test;

import com.andyadc.abatis.builder.xml.XMLConfigBuilder;
import com.andyadc.abatis.io.Resources;
import com.andyadc.abatis.session.Configuration;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.session.SqlSessionFactory;
import com.andyadc.abatis.session.SqlSessionFactoryBuilder;
import com.andyadc.abatis.session.defaults.DefaultSqlSession;
import com.andyadc.abatis.test.entity.User;
import com.andyadc.abatis.test.mapper.UserMapper;
import com.andyadc.abatis.util.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

public class Tests {

    @Test
    public void test_04_SqlSessionFactory() throws IOException {
        // 1. 从 SqlSessionFactory中获取 SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectById(1000L);

        System.out.println(Utils.toJson(user));
    }

    @Test
    public void test_04_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);

        // 执行查询：默认是一个集合参数
        Object[] req = {1000L};
        Object res = sqlSession.selectOne("com.andyadc.abatis.test.mapper.UserMapper.selectById", req);
        System.out.println(Utils.toJson(res));
    }
}
