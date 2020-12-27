package com.chippy.example.redis;

import com.chippy.example.common.respnse.ResponseResult;
import com.chippy.redis.support.api.BooleanRedisTemplate;
import com.chippy.redis.support.api.IntegerRedisTemplate;
import com.chippy.redis.support.api.LongRedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private LongRedisTemplate longRedisTemplate;

    @Resource
    private IntegerRedisTemplate integerRedisTemplate;

    @Resource
    private BooleanRedisTemplate booleanRedisTemplate;

    @GetMapping("/longSet")
    public ResponseResult<Long> longSet(String k1, Long v1) {
        longRedisTemplate.opsForValue().set(k1, v1);
        final Long longValue = longRedisTemplate.opsForValue().get(k1);
        return ResponseResult.success(longValue);
    }

    @GetMapping("/integerSet")
    public ResponseResult<Integer> integerSet(String k1, Integer v1) {
        integerRedisTemplate.opsForValue().set(k1, v1);
        final Integer integerValue = integerRedisTemplate.opsForValue().get(k1);
        return ResponseResult.success(integerValue);
    }

    @GetMapping("/booleanSet")
    public ResponseResult<Boolean> booleanSet(String k1, Boolean v1) {
        booleanRedisTemplate.opsForValue().set(k1, v1);
        final Boolean booleanValue = booleanRedisTemplate.opsForValue().get(k1);
        return ResponseResult.success(booleanValue);
    }

    @GetMapping("/longIncrease")
    public ResponseResult<Long> longIncrease(String k1) {
        final Long increment = longRedisTemplate.opsForValue().increment(k1, 1);
        return ResponseResult.success(increment);
    }

    @GetMapping("/integerIncrease")
    public ResponseResult<Integer> integerIncrease(String k1) {
        final Long increment = integerRedisTemplate.opsForValue().increment(k1, 1);
        return ResponseResult.success(Integer.parseInt(String.valueOf(increment)));
    }

}
