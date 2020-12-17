package com.chippy.example.redisson.task;

import com.chippy.redis.redisson.task.support.DistributedScheduled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: chippy
 * @datetime 2020-12-17 18:01
 */
@Component
@Slf4j
public class TestTask implements DistributedScheduled {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void taskA() {
        log.debug("aaaaaaaaaaa");
    }

}
