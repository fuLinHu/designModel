package com.luban.productserver.controller;

import com.luban.productserver.config.NacosConfigTest;
import com.luban.productserver.entity.Production;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private NacosConfigTest nacosConfigTest;

    @RequestMapping("/selectProductInfoById")
    public String selectProductInfoById(long id) {

        return "获取产品信息成功：：："+id;
    }


    @RequestMapping("/testEntity")
    public Production testEntity(@RequestBody Production production) {
        production.setName("这是传递对象参数");
        return production;
    }
    @RequestMapping("/testResh")
    public String testResh(){
        return  nacosConfigTest.getName();
    }
}
