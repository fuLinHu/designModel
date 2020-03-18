package com.study.javamodel.javadesignmodel.factory.abstractfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:29
 * @Version V1.0
 */
public class CarFactory extends Factory {
    @Override
    public Phone getPhoneFactory(String type) {
       return null;
    }

    @Override
    public Car getCarFactory(String type){
        Car car = null;
        if("dazhong".equals(type)){
            car = new DaZhong();
        }else if("hongqi".equals(type)){
            car = new HongQi();
        }
        return car;
    }
}
