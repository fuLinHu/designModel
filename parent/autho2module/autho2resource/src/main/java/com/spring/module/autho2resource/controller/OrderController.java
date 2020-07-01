package com.spring.module.autho2resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/24 8:35
 * @Version V1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/selectOrderInfoById")
    public String findById() {
        return "根据id获取订单信息";
    }

    @RequestMapping("/saveOrder")
    public String save() {
        return "保存成功------";
    }
}
