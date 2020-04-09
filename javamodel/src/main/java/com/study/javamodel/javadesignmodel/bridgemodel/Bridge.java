package com.study.javamodel.javadesignmodel.bridgemodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/9 11:08
 * @Version V1.0
 */
public abstract class Bridge {
    protected Car car;
    public Bridge(Car car){
        this.car=car;
    }

    public abstract void run();
}
