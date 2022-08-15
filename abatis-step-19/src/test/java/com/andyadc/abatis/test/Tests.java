package com.andyadc.abatis.test;

import com.andyadc.abatis.test.entity.Activity;
import com.andyadc.abatis.test.mapper.ActivityMapper;
import com.andyadc.abatis.util.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tests {

    @Test
    public void test_19_ActivityMapper_selectByActivity() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        ActivityMapper activityMapper = beanFactory.getBean(ActivityMapper.class);

        Activity activity = new Activity();
        activity.setStatus(1);
        System.out.println(Utils.toJSONString(activityMapper.selectByActivity(activity)));
    }
}
