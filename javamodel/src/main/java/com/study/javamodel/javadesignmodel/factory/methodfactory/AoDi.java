package com.study.javamodel.javadesignmodel.factory.methodfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 8:25
 * @Version V1.0
 */
public class AoDi implements Car {
    @Override
    public void run() {
        System.out.println("奥迪车在跑");
    }
}
