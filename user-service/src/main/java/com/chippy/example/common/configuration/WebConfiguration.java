package com.chippy.example.common.configuration;

import com.chippy.core.common.filter.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;

/**
 * web component configuration
 *
 * @author: chippy
 * @datetime 2021/1/21 23:47
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public RequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter(true, true, Collections.emptyList());
    }

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilterFilterRegistrationBean() {
        FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilterFilterRegistrationBean =
            new FilterRegistrationBean<>();
        requestLoggingFilterFilterRegistrationBean.setOrder(1);
        requestLoggingFilterFilterRegistrationBean.addUrlPatterns("/*");
        requestLoggingFilterFilterRegistrationBean.setName("requestLoggingFilter");
        requestLoggingFilterFilterRegistrationBean.setFilter(requestLoggingFilter());
        return requestLoggingFilterFilterRegistrationBean;
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter();
        adapter.setRequestBodyAdvice(Collections.singletonList(requestLoggingFilter()));
        return adapter;
    }

}
