package com.springboot.redis.test;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/11/16 23:37
 * @Version V1.0
 */
@RestController
public class IndexController1 {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test_cluster")
    public void testCluster() throws InterruptedException {
        stringRedisTemplate.opsForValue().set("zhuge", "666");
        System.out.println(stringRedisTemplate.opsForValue().get("zhuge"));
    }

}
