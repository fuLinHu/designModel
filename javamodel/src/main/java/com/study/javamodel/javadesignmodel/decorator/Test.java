package com.study.javamodel.javadesignmodel.decorator;

import java.io.BufferedInputStream;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/19 9:59
 * @Version V1.0
 */

public class Test {
    public static void main(String[] args) {
        HongQi hongQi = new HongQi();
        HongQiDecorator hongQiDecorator = new HongQiDecorator(hongQi);
        hongQiDecorator.run();
    }
}
