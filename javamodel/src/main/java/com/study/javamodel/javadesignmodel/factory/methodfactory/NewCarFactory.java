package com.study.javamodel.javadesignmodel.factory.methodfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:05
 * @Version V1.0
 */
public class NewCarFactory implements Factory {
    @Override
    public Car createFactory() {
        return new NewCar();
    }
}
