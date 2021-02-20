package com.chippy.example;

import com.ejoy.elasticjob.annotation.EnableElasticJob;
import com.ejoy.feign.annotation.EnableFeignClientHelper;
import com.ejoy.tkmapper.EnableMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 用户服务启动类
 *
 * @author: chippy
 * @datetime 2020-12-15 10:57
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableFeignClientHelper
@EnableScheduling
@EnableElasticJob
@EnableMonitor
@MapperScan("com.chippy.example.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
