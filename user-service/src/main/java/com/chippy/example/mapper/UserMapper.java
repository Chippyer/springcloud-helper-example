package com.chippy.example.mapper;

import com.chippy.example.entity.User;
import com.ejoy.tkmapper.A;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>, A {

    @Override
    default Class getC() {
        return User.class;
    }

}
