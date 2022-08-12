package com.andyadc.abatis.test;

import com.andyadc.abatis.io.Resources;
import com.andyadc.abatis.session.SqlSession;
import com.andyadc.abatis.session.SqlSessionFactory;
import com.andyadc.abatis.session.SqlSessionFactoryBuilder;
import com.andyadc.abatis.test.entity.Activity;
import com.andyadc.abatis.test.mapper.ActivityMapper;
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
    public void test_17_ActivityMapper_selectByActivity() {
        ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);

        Activity activity = new Activity();
        activity.setStatus(1);
        List<Activity> list = activityMapper.selectByActivity(activity);
        System.out.println(Utils.toJSONString(list));

        System.out.println("<<<<<<<<<<???>>>>>>>>>>");

        list = activityMapper.selectByActivity(activity);
        System.out.println(Utils.toJSONString(list));
    }
}
