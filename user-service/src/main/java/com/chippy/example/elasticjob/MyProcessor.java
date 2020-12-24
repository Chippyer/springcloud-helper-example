package com.chippy.example.elasticjob;

import com.chippy.elasticjob.support.api.TraceJobProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: chippy
 * @datetime 2020-12-24 16:44
 */
@Component
@Slf4j
public class MyProcessor implements TraceJobProcessor<String> {

    @Override
    public void processCronJob(String cronParam) {
        log.debug("处理定时任务: " + cronParam);
    }

    @Override
    public void createCronJob(String cronParam) {
        log.debug("创建定时任务: " + cronParam);
    }

    @Override
    public void updateCronJob(String cronParam) {
        log.debug("修改定时任务: " + cronParam);
    }

    @Override
    public void removeCronJob(String cronParam) {
        log.debug("移除定时任务: " + cronParam);
    }

}
