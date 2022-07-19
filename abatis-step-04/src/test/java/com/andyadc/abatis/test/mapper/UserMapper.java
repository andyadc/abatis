package com.andyadc.abatis.test.mapper;

import com.andyadc.abatis.test.entity.User;

public interface UserMapper {

    User selectByUserId(Long userId);
}
