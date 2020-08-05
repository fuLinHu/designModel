package com.study.javamodel.javadesignmodel.bridgemodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/3 19:49
 * @Version V1.0
 */
public class HighSpeed extends Bridge{


    public HighSpeed(Car car) {
        super(car);
    }

    @Override
    public void run() {
        System.out.println("在高铁上");
        car.run();
    }
}
