package com.spring.module.consumer.feignclinet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.module.consumer.config.ProFeignConfig;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/9 9:51
 * @Version V1.0
 */
//value="producer" 和 name="producer" 意义相同  同时 value="producer/pro" 会影响调用服务的日志打印
// 因此些feign接口的时候       @FeignClient(value="producer") 中的value或者name  尽量只指向  服务名称
@FeignClient(value="producer",configuration =ProFeignConfig.class)
public interface ProFeignClient {

    @RequestMapping("/pro/test")
    public String test();


}
