package com.andyadc.abatis.test.mapper;

import com.andyadc.abatis.test.entity.User;

public interface UserMapper {

    User selectById(Long id);

    User selectByUser(User user);
}
