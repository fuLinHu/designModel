package com.study.javamodel.javadesignmodel.factory.simplefactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 8:47
 * @Version V1.0
 */
public class NewCar implements Car {
    @Override
    public void run() {
        System.out.println("这个是我新加入的car");
    }
}
