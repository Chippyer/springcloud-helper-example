package com.chippy.example.redis;

import com.chippy.example.common.respnse.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Redis相关接口API
 *
 * @author: chippy
 * @datetime 2020-12-18 16:24
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/set")
    public ResponseResult set(String k1, String k2, String v1) {
        redisTemplate.opsForHash().put(k1, k2, v1);
        final Map<Object, Object> entries = redisTemplate.opsForHash().entries(k1);
        return ResponseResult.success(entries);
    }

}
