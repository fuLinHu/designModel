package com.spring.module.consumer.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.spring.module.consumer.feignclinet.ProFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/9 9:19
 * @Version V1.0
 */
@RestController
@RequestMapping("/con")
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Resource
    private ProFeignClient proFeignClient;



    @RequestMapping("/test")
    public String test(HttpServletRequest httpServletRequest){
        String test = proFeignClient.test();
        List<String> services = discoveryClient.getServices();
        String s = services.get(0);
        int serverPort = httpServletRequest.getServerPort();
        return "this is a consumer!! 【"+s+"】" +test+"【 端口："+serverPort+"】";
    }



}
