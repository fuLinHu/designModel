package com.study.javamodel.javadesignmodel.bridgemodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/3 19:44
 * @Version V1.0
 */
public class Road extends Bridge {

    public Road(Car car) {
        super(car);
    }

    @Override
    public void run() {
        System.out.println("在马路上");
        car.run();
    }
}
