package com.andyadc.abatis.test.mapper;

import com.andyadc.abatis.test.entity.Activity;

import java.util.List;

public interface ActivityMapper {

    List<Activity> selectByActivity(Activity activity);
}
