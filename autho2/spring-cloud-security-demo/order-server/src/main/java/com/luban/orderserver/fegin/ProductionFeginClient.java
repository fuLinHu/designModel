package com.luban.orderserver.fegin;

import com.luban.orderserver.entity.Production;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/6/8 9:48
 * @Version V1.0
 */
@FeignClient(value = "product-server",path = "/product")
public interface ProductionFeginClient {
    //1.单参数的时候 fegin上一定要加@RequestParam 注解
    //如果传递对象则需要再fegin和被调用段  对象参数添加@RequestBody注解 调用端可以不加

    @RequestMapping("/selectProductInfoById")
    public String selectProductInfoById(@RequestParam("id") long id);
    @RequestMapping("/testEntity")
    public Production testEntity(@RequestBody Production production);
}
