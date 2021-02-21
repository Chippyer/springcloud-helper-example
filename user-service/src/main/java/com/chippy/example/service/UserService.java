package com.chippy.example.service;

import cn.hutool.json.JSONUtil;
import com.chippy.example.entity.User;
import com.chippy.example.mapper.UserMapper;
import com.ejoy.core.common.utils.ObjectsUtil;
import com.ejoy.tkmapper.annotation.AutoMonitor;
import com.ejoy.tkmapper.support.api.IMonitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private IMonitorService monitorService;

    @Override
    @Transactional
    public User updateStatus(Long id, Boolean status) {
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
        monitorService.process(user, JSONUtil.toJsonStr(user));
        return user;
    }

    @Override
    @Transactional
    @AutoMonitor
    public User updateName(Long id, String name) {
        User user = userMapper.selectByPrimaryKey(id);
        if (ObjectsUtil.isEmpty(user)) {
            user = new User();
            user.setName(name);
            user.setStatus(false);
            userMapper.insertSelective(user);
        } else {
            user.setName(name);
            userMapper.updateByPrimaryKeySelective(user);
        }
        return user;
    }
}
