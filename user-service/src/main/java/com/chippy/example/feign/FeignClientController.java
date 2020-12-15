package com.chippy.example.feign;

import com.chippy.example.common.respnse.ResponseResult;
import com.chippy.feign.support.api.clients.GenericFeignClient;
import com.chippy.feign.support.api.clients.ListFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试FeignClient
 *
 * @author: chippy
 * @datetime 2020-12-15 10:59
 */
@RestController
@RequestMapping("/test/feignClient")
public class FeignClientController {

    /**
     * 获取单个对象返回测试
     *
     * @return com.chippy.example.common.respnse.ResponseResult<com.chippy.example.feign.OrderInfoResult>
     * @author chippy
     */
    @GetMapping("/1")
    public ResponseResult<OrderInfoResult> getOrderInfo(@RequestParam("orderNo") String orderNo) {
        return ResponseResult.success(GenericFeignClient.invoke(OrderInfoResult.class, "getOrderInfo", orderNo));
    }

    /**
     * 获取集合返回测试-不使用处理器请求
     *
     * @return com.chippy.example.common.respnse.ResponseResult<java.util.List < com.chippy.example.feign.OrderInfoResult>>
     * @author chippy
     */
    @GetMapping("/2")
    public ResponseResult<List<OrderInfoResult>> getHistoryOrderInfoList(@RequestParam("userId") String userId) {
        return ResponseResult.success(ListFeignClient.invoke(OrderInfoResult.class, "getHistoryOrderInfoList", userId));
    }

}
