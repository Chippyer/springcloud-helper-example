package com.chippy.example.service;

import com.chippy.example.entity.User;
import com.chippy.example.mapper.UserMapper;
import com.ejoy.core.common.utils.ObjectsUtil;
import com.ejoy.tkmapper.MonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: chippy
 * @datetime 2021-02-19 17:56
 */
@Service
public class UserService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MonitorService monitorService;

    @Override
    public Long updateStatus(Long id, Boolean status) {
        User user = userMapper.selectByPrimaryKey(id);
        if (ObjectsUtil.isEmpty(user)) {
            user = new User();
            user.setName("新增默认名称");
            user.setStatus(status);
            userMapper.insertSelective(user);
        } else {
            user.setStatus(status);
            userMapper.updateByPrimaryKeySelective(user);
        }
        monitorService.update(user);
        return user.getId();
    }
}
