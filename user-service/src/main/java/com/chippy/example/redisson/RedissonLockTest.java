package com.chippy.example.redisson;

import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试RedissonLock
 *
 * @author: chippy
 * @datetime 2020/12/16 23:29
 */
@Slf4j
public class RedissonLockTest {

    public static void main(String[] args) {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(10, new TestReleaseLock());
    }

    public static class TestReleaseLock implements Runnable {
        @Override
        public void run() {
            final HttpRequest request = HttpUtil.createGet("localhost:9002/redisson/lock2?skuId=1&releaseStock=9");
            final HttpResponse response = request.execute();
            System.out.println(Thread.currentThread().getName() + "-response-" + response.body());
        }
    }

}
