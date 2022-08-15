package com.andyadc.abatis.test;

import com.andyadc.abatis.io.Resources;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.session.SqlSessionFactory;
import com.andyadc.abatis.session.SqlSessionFactoryBuilder;
import com.andyadc.abatis.test.entity.Activity;
import com.andyadc.abatis.test.mapper.ActivityMapper;
import com.andyadc.abatis.util.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

public class Tests {

    @Test
    public void test_18_ActivityMapper_selectByActivity() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 2. 请求对象
        Activity activity = new Activity();
        activity.setStatus(1);

        // 3. 第一组：SqlSession
        // 3.1 开启 Session
        SqlSession sqlSession01 = sqlSessionFactory.openSession();
        // 3.2 获取映射器对象
        ActivityMapper activityMapper01 = sqlSession01.getMapper(ActivityMapper.class);
        System.out.println(Utils.toJSONString(activityMapper01.selectByActivity(activity)));
        sqlSession01.close();

        System.out.println("<<<<<<<<<<???>>>>>>>>>>");

        // 4. 第一组：SqlSession
        // 4.1 开启 Session
        SqlSession sqlSession02 = sqlSessionFactory.openSession();
        // 4.2 获取映射器对象
        ActivityMapper activityMapper02 = sqlSession02.getMapper(ActivityMapper.class);
        System.out.println(Utils.toJSONString(activityMapper02.selectByActivity(activity)));
        sqlSession02.close();
    }
}
