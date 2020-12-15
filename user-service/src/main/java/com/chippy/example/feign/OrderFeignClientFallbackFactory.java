package com.chippy.example.feign;

import com.chippy.example.common.respnse.ResponseResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单内部服务FeignClientFallback定义
 *
 * @author: chippy
 * @datetime 2020-12-15 11:01
 */
@Service
@Slf4j
public class OrderFeignClientFallbackFactory implements FallbackFactory<OrderFeignClient> {

    @Override
    public OrderFeignClient create(Throwable cause) {
        return new OrderFeignClient() {
            @Override
            public ResponseResult<BigDecimal> byOrderNo(String orderNo) {
                log.error(
                    "OrderFeignClientFallback-byOrderNo-params[" + orderNo + "] fallback-cause-" + cause.getMessage());
                return null;
            }

            @Override
            public ResponseResult<OrderInfoResult> getOrderInfo(String orderNo) {
                log.error("OrderFeignClientFallback-getOrderInfo-params[" + orderNo + "] fallback-cause-" + cause
                    .getMessage());
                return null;
            }

            @Override
            public ResponseResult<List<OrderInfoResult>> getHistoryOrderInfoList(String userId) {
                log.error(
                    "OrderFeignClientFallback-getHistoryOrderInfoList-params[" + userId + "] fallback-cause-" + cause
                        .getMessage());
                return null;
            }
        };
    }
}
