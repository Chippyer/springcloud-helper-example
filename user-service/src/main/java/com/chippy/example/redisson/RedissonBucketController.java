package com.chippy.example.redisson;

import com.chippy.example.common.respnse.ResponseResult;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: chippy
 * @datetime 2020/12/18 2:05
 */
@RestController
@RequestMapping("/redisson/bucket")
public class RedissonBucketController {

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/set")
    public ResponseResult<String> set(String k, String v) {
        final RBucket<String> bucket = redissonClient.getBucket(k);
        bucket.set(v);
        return ResponseResult.success(bucket.get());
    }

    @GetMapping("/get")
    public ResponseResult<String> get(String k) {
        final RBucket<String> bucket = redissonClient.getBucket(k);
        final String s = bucket.get();
        return ResponseResult.success(s);
    }

}
