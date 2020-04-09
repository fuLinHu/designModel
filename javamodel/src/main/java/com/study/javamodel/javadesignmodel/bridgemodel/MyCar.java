package com.study.javamodel.javadesignmodel.bridgemodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/9 11:11
 * @Version V1.0
 */
public class MyCar extends Bridge {

    public MyCar(Car car) {
        super(car);
    }

    @Override
    public void run() {
        car.run();
    }
}
