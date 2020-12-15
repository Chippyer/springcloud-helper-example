package com.chippy.example.feign.processor;

import com.chippy.feign.support.api.processor.AbstractLogFeignClientProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chippy
 * @datetime 2020-12-15 15:50
 */
@Component
public class OrderLogFeignClientProcessor extends AbstractLogFeignClientProcessor {
    @Override
    public List<String> getIncludePathPattern() {
        return new ArrayList<String>() {{
            add("/order/*");
        }};
    }
}
