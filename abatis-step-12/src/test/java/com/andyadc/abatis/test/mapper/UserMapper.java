package com.andyadc.abatis.test.mapper;

import com.andyadc.abatis.annotations.Select;
import com.andyadc.abatis.test.entity.User;

public interface UserMapper {

    @Select(value = "select * from t_user where id = #{id}")
    User selectById(Long id);
}
