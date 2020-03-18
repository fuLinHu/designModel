package com.study.javamodel.javadesignmodel.factory.abstractfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:27
 * @Version V1.0
 */
public abstract  class Factory {
    public abstract Phone getPhoneFactory(String type);
    public abstract Car getCarFactory(String type);
}
