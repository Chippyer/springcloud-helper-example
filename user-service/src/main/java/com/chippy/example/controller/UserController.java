package com.chippy.example.controller;

import com.chippy.example.entity.User;
import com.chippy.example.service.IUserService;
import com.ejoy.core.common.response.DefaultResultImpl;
import com.ejoy.tkmapper.support.api.IMonitorService;
import com.ejoy.tkmapper.support.domain.MonitorOperationLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关API
 *
 * @author: chippy
 * @datetime 2021-02-05 10:27
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/1")
    public DefaultResultImpl<String> test1() {
        return DefaultResultImpl.success("hello world");
    }

    @GetMapping("/21")
    public DefaultResultImpl<User> test21(Long id, Boolean status) {
        return DefaultResultImpl.success(userService.updateStatus(id, status));
    }

    @GetMapping("/22")
    public DefaultResultImpl<User> test22(Long id, String name) {
        return DefaultResultImpl.success(userService.updateName(id, name));
    }

    @GetMapping("/23")
    public DefaultResultImpl<User> test23(Long id, String name, Boolean status) {
        return DefaultResultImpl.success(userService.updateNameAndStatus(id, name, status));
    }

    @Resource
    private IMonitorService monitorService;

    @GetMapping("/3")
    public DefaultResultImpl<List<MonitorOperationLogInfo>> test3(Long id, String field, int endIndex) {
        return DefaultResultImpl.success(monitorService.get(User.class, String.valueOf(id), field, endIndex));
    }

}
