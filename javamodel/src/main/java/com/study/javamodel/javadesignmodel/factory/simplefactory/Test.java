package com.study.javamodel.javadesignmodel.factory.simplefactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 8:30
 * @Version V1.0
 */
public  class Test {
    //简单工厂不满足 开闭原则
    //为所有车创建一个工厂
    public static void main(String[] args) {
        Car car = CarFactory.getCar("aodi");
        car.run();
        car = CarFactory.getCar("dazhong");
        car.run();
        car = CarFactory.getCar("hongqi");
        car.run();
        car = CarFactory.getCar("newcar");
        car.run();
    }
}
