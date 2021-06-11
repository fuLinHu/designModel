package com.luban.orderserver.controller;

import com.luban.orderserver.entity.Production;
import com.luban.orderserver.fegin.ProductionFeginClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private ProductionFeginClient productionFeginClient;


    @RequestMapping("/selectOrderInfoByIdAndUsername")
    public String selectOrderInfoByIdAndUsername(long id,String username) {

        return "获取订单信息成功";
    }

    @RequestMapping("/getProcuct")
    public String getProcuct(long id){
        return productionFeginClient.selectProductInfoById(id);
    }


    @RequestMapping("/testEntitytestEntity")
    public Production testEntity(Production production){
        return productionFeginClient.testEntity(production);
    }
}
