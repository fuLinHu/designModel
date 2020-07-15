package com.tuling.entity;

import lombok.Data;

import javax.annotation.PostConstruct;

/**
 * Created by smlz on 2019/11/17.
 */
@Data
public class ProductInfo {

    private String productNo= "001";

    private String productName= "手表";

    private String productStore = "北京仓库";

    private double productPrice=100;
}
