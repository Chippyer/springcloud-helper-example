package com.chippy.example.elasticjob;

import com.ejoy.elasticjob.support.api.AbstractTraceJob;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.springframework.stereotype.Component;

/**
 * @author: chippy
 * @datetime 2020-12-24 16:46
 */
@Component
public class MyJob extends AbstractTraceJob<String> {
    @Override
    protected Class<String> getGenericClass() {
        return String.class;
    }

    @Override
    public void execute(ShardingContext shardingContext) {
        super.doExecute(shardingContext.getJobName(), shardingContext.getJobParameter());
    }
}
