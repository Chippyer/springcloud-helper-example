package com.chippy.example.feign;

import cn.hutool.json.JSONUtil;
import com.chippy.example.common.respnse.ResponseResult;
import com.ejoy.core.common.utils.ObjectsUtil;
import com.ejoy.feign.support.api.clients.GenericFeignClient;
import com.ejoy.feign.support.api.clients.ListFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 测试FeignClient
 *
 * @author: chippy
 * @datetime 2020-12-15 10:59
 */
@RestController
@RequestMapping("/test/feignClient")
@Slf4j
public class FeignClientController {

    @Resource
    private OrderFeignClient orderFeignClient;

    /**
     * 获取单个对象返回测试
     *
     * @author chippy
     */
    @GetMapping("/0")
    public ResponseResult<OrderInfoResult> getOrderInfo0(@RequestParam("orderNo") String orderNo) {
        log.debug("请求订单服务查询订单信息参数-" + orderNo);
        final ResponseResult<OrderInfoResult> orderInfoResult = orderFeignClient.getOrderInfo(orderNo);
        log.debug("请求订单服务查询订单信息结果-" + JSONUtil.toJsonStr(orderInfoResult));
        if (ObjectsUtil.isEmpty(orderInfoResult)) {
            log.error("查询订单信息结果为空");
            return ResponseResult.success();
        }
        if (orderInfoResult.getCode() != 0) {
            log.error("查询订单信息发生异常-" + orderInfoResult.getErrorMsg());
            return ResponseResult.fail(orderInfoResult.getCode(), orderInfoResult.getErrorMsg());
        }
        return ResponseResult.success(orderInfoResult.getData());
    }

    /**
     * 获取单个对象返回测试
     *
     * @return com.chippy.example.common.respnse.ResponseResult<com.chippy.example.feign.OrderInfoResult>
     * @author chippy
     */
    @GetMapping("/1")
    public ResponseResult<OrderInfoResult> getOrderInfo(@RequestParam("orderNo") String orderNo) {
        return ResponseResult.success(GenericFeignClient.invokeIfExThrow(OrderInfoResult.class, "getOrderInfo", orderNo));
    }

    /**
     * 获取集合返回测试-不使用处理器请求
     *
     * @return com.chippy.example.common.respnse.ResponseResult<java.util.List < com.chippy.example.feign.OrderInfoResult>>
     * @author chippy
     */
    @GetMapping("/2")
    public ResponseResult<List<OrderInfoResult>> getHistoryOrderInfoList(@RequestParam("userId") String userId) {
        return ResponseResult
            .success(ListFeignClient.invokeIfExThrow(OrderInfoResult.class, "getHistoryOrderInfoList", userId));
    }

    /**
     * 测试访问/price/*查看是否执行处理器
     *
     * @return java.math.BigDecimal
     * @author chippy
     */
    @GetMapping("/3")
    public ResponseResult<BigDecimal> byOrderNo(@RequestParam("orderNo") String orderNo) {
        return ResponseResult.success(GenericFeignClient.invoke(BigDecimal.class, "byOrderNo", orderNo));
    }

}
