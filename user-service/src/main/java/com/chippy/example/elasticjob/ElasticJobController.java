package com.chippy.example.elasticjob;

import cn.hutool.core.date.DateTime;
import com.chippy.core.common.utils.DateUtil;
import com.chippy.elasticjob.support.domain.JobInfo;
import com.chippy.example.common.respnse.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: chippy
 * @datetime 2020-12-24 16:52
 */
@RestController
@RequestMapping("/elasticJob")
public class ElasticJobController {

    @Resource
    private MyHandler myHandler;

    @PostMapping("/createJob")
    public ResponseResult<String> createJob(String originalJobName, String jobParameter, String invokeDateTime) {
        final JobInfo jobInfo = JobInfo.buildSimpleJobInfo(originalJobName, jobParameter,
            new DateTime(invokeDateTime, DateUtil.YYYY_MM_DD_HH_MM_SS));
        myHandler.createJob(jobInfo);
        return ResponseResult.success("createJob: " + originalJobName);
    }

    @PostMapping("/updateJob")
    public ResponseResult<String> updateJob(String originalJobName, String jobParameter, String invokeDateTime) {
        final JobInfo jobInfo = JobInfo.buildSimpleJobInfo(originalJobName, jobParameter,
            new DateTime(invokeDateTime, DateUtil.YYYY_MM_DD_HH_MM_SS));
        myHandler.updateJob(jobInfo);
        return ResponseResult.success("updateJob: " + originalJobName);
    }

    @PostMapping("/removeJob")
    public ResponseResult<String> removeJob(String originalJobName) {
        myHandler.removeJob(originalJobName);
        return ResponseResult.success("removeJob: " + originalJobName);
    }

}
