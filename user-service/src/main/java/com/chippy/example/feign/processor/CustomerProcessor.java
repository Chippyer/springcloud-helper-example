package com.chippy.example.feign.processor;

import com.chippy.example.feign.OrderInfoResult;
import com.chippy.example.feign.service.ServiceA;
import com.ejoy.core.common.response.Result;
import com.ejoy.core.common.utils.ObjectsUtil;
import com.ejoy.feign.support.api.processor.FeignClientProcessor;
import com.ejoy.feign.support.definition.FeignClientDefinition;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义FeignClient处理器
 *
 * @author: chippy
 * @datetime 2020-12-15 11:25
 */
@Slf4j
@Component
public class CustomerProcessor implements FeignClientProcessor {

    @Resource
    private ServiceA serviceA;

    @Override
    public List<String> getIncludePathPattern() {
        return new ArrayList<String>() {{
            add("/order/**");
        }};
    }

    @Override
    public Object[] processBefore(FeignClientDefinition.Element element, Object[] param) {
        log.debug("我是自定义处理器-before");
        final String hello = serviceA.hello();
        log.debug("hello -" + hello);
        return param;
    }

    @Override
    public Object processAfter(FeignClientDefinition.Element element, Object response) {
        log.debug("我是自定义处理器-after");
        final Result<OrderInfoResult> result = (Result<OrderInfoResult>)response;
        final OrderInfoResult data = result.getData();
        if (ObjectsUtil.isNotEmpty(data)) {
            data.setName("补充用户名");
            result.setData(data);
        }
        return result;
    }

    @SneakyThrows
    @Override
    public void processException(FeignClientDefinition.Element element, Exception e) {
        throw e;
    }

}
