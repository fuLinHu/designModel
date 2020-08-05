package com.study.javamodel.javadesignmodel.proxy.cglib;

import commit.entity.User;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/3 8:16
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\java\\java_workapace");

        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(UserServiceImpl.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());

        //这里的creat方法就是正式创建代理类
        UserServiceImpl proxy = (UserServiceImpl)enhancer.create();
        //调用代理类的eat方法
        proxy.add(new User("fulinhu",30,new Date(),188));
        proxy.delete("1111");
        proxy.updte(new User("fulinhu",31,new Date(),188));
        proxy.select();
    }
}
