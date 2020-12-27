package com.chippy.example.controller;

import com.chippy.core.common.utils.UUIDUtil;
import com.chippy.example.common.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: chippy
 * @datetime 2020-12-15 11:18
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/getOrderInfo")
    public ResponseResult<OrderInfoResult> getOrderInfo(@RequestParam("orderNo") String orderNo) {
        OrderInfoResult orderInfoResult = new OrderInfoResult();
        orderInfoResult.setAddress(UUIDUtil.generateUuid() + "-北京");
        orderInfoResult.setOrderNo(UUIDUtil.generateUuid() + "-订单号");
        orderInfoResult.setUserId(UUIDUtil.generateUuid() + "-userId");
        return ResponseResult.success(orderInfoResult);
    }

    @GetMapping("/getHistoryOrderInfoList")
    public ResponseResult<List<OrderInfoResult>> getHistoryOrderInfoList(@RequestParam("userId") String userId) {
        OrderInfoResult orderInfoResult1 = new OrderInfoResult();
        orderInfoResult1.setAddress(UUIDUtil.generateUuid() + "-北京");
        orderInfoResult1.setOrderNo(UUIDUtil.generateUuid() + "-订单号");
        orderInfoResult1.setUserId(UUIDUtil.generateUuid() + "-userId");

        OrderInfoResult orderInfoResult2 = new OrderInfoResult();
        orderInfoResult2.setAddress(UUIDUtil.generateUuid() + "-北京");
        orderInfoResult2.setOrderNo(UUIDUtil.generateUuid() + "-订单号");
        orderInfoResult2.setUserId(UUIDUtil.generateUuid() + "-userId");
        //return ResponseResult.success(Arrays.asList(orderInfoResult1, orderInfoResult2));
        return ResponseResult.fail(1, "自定义订单错误");
    }

}
