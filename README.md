# springcloud-helper-example
<h1>springcloud-helper-example的组件使用示例</h1>
<h2>1. springcloud-feign-helper</h2>
第一步
在我们需要使用的服务中加入jar包引用例如

```xml
<dependency>
     <groupId>com.chippy</groupId>
     <artifactId>springcloud-feign-helper</artifactId>
     <version>2.0</version>
 </dependency>
```
第二步
在启动类上加入注解@EnableFeignClientHelper例如

```java
package com.chippy.example;

import com.chippy.feign.annotation.EnableFeignClientHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户服务启动类
 *
 * @author: chippy
 * @datetime 2020-12-15 10:57
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableFeignClientHelper
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
```

完成上述两步骤后即可使用[GenericFeignClient]或[ListFeignClient]类进行调用
<strong>需要注意的是，您的response包装实体需要实现Result<T>接口，具体详见DefaultResultImpl类</strong>

第三步
恭喜你完成上述两步骤就已经可以进行基本的使用了。在此我们讲一下扩展功能。
一般来说我们希望在请求的前后做一些自己的操作处理，以便业务通用功能的封装或自定义操作。
此时我们可以关注[FeignClientProcessor]接口，具体详见[LogFeignClientProcessor]日志功能的实现。

使用步骤如下
第一步 
实现[FeignClientProcessor]接口做自己的要自定义的工作

```java
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
```

第二步 
在resource/META-INF/services/下创建文件(不用指定文件类型)名为
com.chippy.feign.support.api.processor.FeignClientProcessor
文件内容为实现类，多个以换行处理
com.chippy.example.feign.CustomerProcessor

恭喜你到这里你已经完成了自定义的处理器的工作啦。




逐步更新中...
