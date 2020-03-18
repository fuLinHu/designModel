package com.study.javamodel.javadesignmodel.factory.methodfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:04
 * @Version V1.0
 */
public class NewCar implements Car {

    @Override
    public void run() {
        System.out.println("我是新创建的car");
    }
}
