package com.study.javamodel.javadesignmodel.factory.simplefactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 8:27
 * @Version V1.0
 */
public class CarFactory {
    public static  Car getCar(String type){
        Car car = null;
        if("dazhong".equals(type)){
            car = new DaZhong();
        }else if("hongqi".equals(type)){
            car = new HongQi();
        }else if("aodi".equals(type)){
            car = new AoDi();
        }//当添加新的车的种类的时候只需要修改此处的代码即可
        else if("newcar".equals(type)){
            car = new NewCar();
        }
        return car;
    }
}
