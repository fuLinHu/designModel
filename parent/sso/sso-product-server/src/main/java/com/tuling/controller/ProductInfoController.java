package com.tuling.controller;

import com.tuling.entity.ProductInfo;
import com.tuling.mapper.ProductInfoMapper;
import com.tuling.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by smlz on 2019/11/17.
 */
@RestController
@RequestMapping("/product")
public class ProductInfoController {


    @Autowired
    private ProductInfoMapper productInfoMapper;

    @RequestMapping("/selectProductInfoById/{productNo}")
    public Result<ProductInfo> selectProductInfoById(@PathVariable("productNo") String productNo) {

        //ProductInfo productInfo = productInfoMapper.selectProductInfoById(productNo);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("小米手环");
        productInfo.setProductNo("1234567890");
        productInfo.setProductPrice(300.9);
        productInfo.setProductStore("北京仓库");
        return Result.success(productInfo);
    }


}
