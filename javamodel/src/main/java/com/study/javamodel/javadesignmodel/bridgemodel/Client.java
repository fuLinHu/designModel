package com.study.javamodel.javadesignmodel.bridgemodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/9 11:15
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
//        Car car = new HongQi();
//        Bridge bridge = new MyCar(car);
//        bridge.run();
//
//        car =new AoDi();
//        bridge = new MyCar(car);
//        bridge.run();

        Car car = new HongQi();
        Bridge bridge = new Road(car);
        bridge = new HighSpeed(car);
        bridge.run();
    }
}
