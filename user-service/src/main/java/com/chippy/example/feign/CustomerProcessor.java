package com.chippy.example.feign;

import com.chippy.common.response.Result;
import com.chippy.feign.support.api.processor.FeignClientProcessor;
import com.chippy.feign.support.definition.FeignClientDefinition;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义FeignClient处理器
 *
 * @author: chippy
 * @datetime 2020-12-15 11:25
 */
@Slf4j
public class CustomerProcessor implements FeignClientProcessor {
    @Override
    public List<String> getIncludePathPattern() {
        return new ArrayList<String>() {{
            add("/order/getOrderInfo");
        }};
    }

    @Override
    public Object[] processBefore(FeignClientDefinition.Element element, Object[] param) {
        log.debug("我是自定义处理器-before");
        return param;
    }

    @Override
    public Object processAfter(FeignClientDefinition.Element element, Object response) {
        log.debug("我是自定义处理器-after");
        final Result<OrderInfoResult> result = (Result<OrderInfoResult>)response;
        final OrderInfoResult data = result.getData();
        data.setName("补充用户名");
        result.setData(data);
        return result;
    }

    @SneakyThrows
    @Override
    public void processException(FeignClientDefinition.Element element, Exception e) {
        throw e;
    }
}
