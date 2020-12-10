package com.forest.tiger.rabbit.springbootrabbit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/3 9:20
 * @Version V1.0
 */
@RestController
@RequestMapping("/rabbit")
public class RunController {
    @Resource
    private Product product;
    @RequestMapping("/test")
    public String run(){
        product.run();
        return "tiger";
    }
}
