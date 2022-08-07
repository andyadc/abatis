package com.andyadc.abatis.test.mapper;

import com.andyadc.abatis.test.entity.User;

import java.util.List;

public interface UserMapper {

    User selectById(Long id);

    User selectByUser(User user);

    List<User> selectByStatus(User user);

    int updateAgeById(User user);

    User insertUser(User user);

    int deleteByName(User user);
}
