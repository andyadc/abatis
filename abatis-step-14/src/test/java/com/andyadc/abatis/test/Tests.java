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
import java.util.concurrent.ThreadLocalRandom;

public class Tests {

    private SqlSession sqlSession;

    @BeforeEach
    public void init() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_14_ActivityMapper_selectByActivityId() {
        ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);

        Activity activity = activityMapper.selectByActivityId(100001L);
        System.out.println(Utils.toJSONString(activity));
    }

    @Test
    public void test_14_ActivityMapper_insert() {
        ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);

        Activity activity = new Activity();
        activity.setActivityId(ThreadLocalRandom.current().nextLong());
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试数据插入");
        activity.setStatus(1);

        Integer result = activityMapper.insert(activity);
        System.out.println(result);

        sqlSession.commit();

        System.out.println(activity.getId());
    }
}
