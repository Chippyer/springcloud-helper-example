package com.chippy.example.feign;

import com.chippy.example.common.respnse.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单内部服务FeignClient接口定义
 *
 * @author: chippy
 * @datetime: 2020-12-15 11:00
 */
@FeignClient(name = "ORDER-SERVICE", fallbackFactory = OrderFeignClientFallbackFactory.class)
public interface OrderFeignClient {

    @GetMapping("/order/getOrderInfo")
    ResponseResult<OrderInfoResult> getOrderInfo(@RequestParam("orderNo") String orderNo);

    @GetMapping("/order/getHistoryOrderInfoList")
    ResponseResult<List<OrderInfoResult>> getHistoryOrderInfoList(@RequestParam("userId") String userId);

}
