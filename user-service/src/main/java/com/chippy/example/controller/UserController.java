package com.chippy.example.controller;

import com.chippy.example.service.IUserService;
import com.ejoy.core.common.response.DefaultResultImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping("/2")
    public DefaultResultImpl<Long> test2(Long id, Boolean status ) {
        return DefaultResultImpl.success(userService.updateStatus(id, status));
    }

}
