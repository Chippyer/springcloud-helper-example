package com.chippy.example.elasticjob;

import com.chippy.elasticjob.support.api.AbstractTraceJobHandler;
import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: chippy
 * @datetime 2020-12-24 16:46
 */
@Component
public class MyHandler extends AbstractTraceJobHandler {

    @Resource
    private MyJob myJob;

    @Override
    public ElasticJob getJob() {
        return myJob;
    }

    @Override
    public String getErrorMessageFormat() {
        return "我的自定义任务处理器";
    }
}
