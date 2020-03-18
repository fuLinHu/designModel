package com.study.javamodel.javadesignmodel.factory.methodfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 8:55
 * @Version V1.0
 */
public class HongQiFactory implements Factory {
    @Override
    public Car createFactory() {
        return new HongQi();
    }
}
