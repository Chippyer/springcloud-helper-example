package com.chippy.example.feign.processor;

import com.chippy.feign.support.api.processor.AbstractMonitorFeignClientProcessor;
import com.chippy.feign.support.definition.FeignClientDefinition;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单接口请求时间监控
 *
 * @author: chippy
 * @datetime 2020-12-15 15:48
 */
@Component
@Order(1)
public class OrderMonitorProcessor extends AbstractMonitorFeignClientProcessor {
    @Override
    public List<String> getIncludePathPattern() {
        return new ArrayList<String>() {{
            add("/order/*");
        }};
    }

    @SneakyThrows
    @Override
    public void processException(FeignClientDefinition.Element element, Exception e) {
        throw e;
    }
}
