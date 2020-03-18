package com.study.javamodel.javadesignmodel.factory.abstractfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:37
 * @Version V1.0
 */
public class FactoryCreator  {
    public static Factory getFactory(String type){
        Factory factory = null;
        if("car".equals(type)){
            factory = new CarFactory();
        }else if("phone".equals(type)){
            factory = new PhoneFactory();
        }
        return factory;
    }
}
