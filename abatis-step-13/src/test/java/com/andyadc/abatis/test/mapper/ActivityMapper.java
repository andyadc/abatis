package com.andyadc.abatis.test.mapper;

import com.andyadc.abatis.test.entity.Activity;

public interface ActivityMapper {

    Activity selectByActivityId(Long activityId);
}
