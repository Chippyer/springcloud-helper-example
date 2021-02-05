package com.chippy.example.controller;

import com.ejoy.core.common.response.DefaultResultImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/1")
    public DefaultResultImpl<String> test1() {
        return DefaultResultImpl.success("hello world");
    }

}
