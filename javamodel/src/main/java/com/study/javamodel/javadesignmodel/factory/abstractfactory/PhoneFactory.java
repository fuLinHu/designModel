package com.study.javamodel.javadesignmodel.factory.abstractfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:29
 * @Version V1.0
 */
public class PhoneFactory extends Factory {
    @Override
    public Phone getPhoneFactory(String type) {
       Phone phone = null;
       if("huawei".equals(type)){
           phone = new HuaWei();
       }else if("xiaomi".equals(type)){
           phone = new XiaoMi();
       }
       return phone;
    }

    @Override
    public Car getCarFactory(String type){
        return null;
    }
}
