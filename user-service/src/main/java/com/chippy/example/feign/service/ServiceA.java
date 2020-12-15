package com.chippy.example.feign.service;

import org.springframework.stereotype.Service;

/**
 * @author: chippy
 * @datetime 2020-12-15 15:05
 */
@Service
public class ServiceA {

    public String hello() {
        return "hello world";
    }

}
