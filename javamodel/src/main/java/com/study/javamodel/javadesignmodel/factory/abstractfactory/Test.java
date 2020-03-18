package com.study.javamodel.javadesignmodel.factory.abstractfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:41
 * @Version V1.0
 */
public class Test {
    //一个工厂可以创建多个 产品
    public static void main(String[] args) {
        Factory carFactory = FactoryCreator.getFactory("car");
        Car car = carFactory.getCarFactory("dazhong");
        car.run();
        car = carFactory.getCarFactory("hongqi");
        car.run();
        Factory phoneFactory = FactoryCreator.getFactory("phone");
        Phone phone = phoneFactory.getPhoneFactory("huawei");
        phone.call();
        phone = phoneFactory.getPhoneFactory("xiaomi");
        phone.call();

    }
}
