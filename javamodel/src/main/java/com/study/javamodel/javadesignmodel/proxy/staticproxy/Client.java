package com.study.javamodel.javadesignmodel.proxy.staticproxy;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 15:34
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Car daiJiaProxy = new DaiJiaProxy();
        daiJiaProxy.run();
    }
}
