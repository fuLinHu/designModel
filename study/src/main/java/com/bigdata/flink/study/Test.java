package com.bigdata.flink.study;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/31 17:58
 * @Version V1.0
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new Date(1603069910000l).toLocaleString());
        System.out.println(new Date(1603069907000l).toLocaleString());


    }
}
/*
id105,1603069910000,1
id105,1603069911000,1
id105,1603069912000,1
id105,1603069913000,1
id105,1603069914000,1
id105,1603069910000,1
id105,1603069914000,6
id105,1603069914900,6
id105,1603069919900,6*/
