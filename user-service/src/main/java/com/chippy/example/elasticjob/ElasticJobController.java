package com.chippy.example.elasticjob;

import com.chippy.elasticjob.support.api.TraceJobOperationService;
import com.chippy.elasticjob.support.domain.JobInfo;
import com.chippy.elasticjob.support.enums.JobStatusEnum;
import com.chippy.example.common.respnse.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chippy
 * @datetime 2020-12-24 16:52
 */
@RestController
@RequestMapping("/elasticJob")
public class ElasticJobController {

    @Resource
    private MyHandler myHandler;

    @Resource
    private TraceJobOperationService traceJobOperationService;

    @PostMapping("/createJob")
    public ResponseResult<String> createJob(String originalJobName, String jobParameter, String invokeDateTime) {
        myHandler.createJob(originalJobName, jobParameter, invokeDateTime);
        return ResponseResult.success("createJob: " + originalJobName);
    }

    @PostMapping("/updateJob")
    public ResponseResult<String> updateJob(String originalJobName, String jobParameter, String invokeDateTime) {
        myHandler.updateJob(originalJobName, jobParameter, invokeDateTime);
        return ResponseResult.success("updateJob: " + originalJobName);
    }

    @PostMapping("/removeJob")
    public ResponseResult<String> removeJob(String originalJobName) {
        myHandler.removeJob(originalJobName);
        return ResponseResult.success("removeJob: " + originalJobName);
    }

    @GetMapping("/byCondition")
    public ResponseResult<List<JobInfo>> list(String originalJobName, String status) {
        return ResponseResult
            .success(traceJobOperationService.byOriginalJobName(originalJobName, JobStatusEnum.valueOf(status)));
    }

}
