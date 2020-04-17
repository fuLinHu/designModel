package com.study.javamodel.javadesignmodel.proxy.dynamicproxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 16:19
 * @Version V1.0
 */
@Slf4j
public class InveHanderProxy implements InvocationHandler {

    //被代理的接口
    private Object interfaceProxy;

    public void setInterfaceProxy(Object interfaceProxy){
        this.interfaceProxy = interfaceProxy;
    }

    //获取代理对象
    public Object getProxy(){
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaceProxy.getClass().getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("方法名称："+method.getName());
        log.info("参数："+ JSONArray.fromObject(args).toString());
        return method.invoke(interfaceProxy,args);
    }


}
