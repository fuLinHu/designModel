package com.study.javamodel.javadesignmodel.adaptermodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/17 13:27
 * @Version V1.0
 */
public class Adapter implements NetToSub {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void handeler() {
        adaptee.request();
    }
}
