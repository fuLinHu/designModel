package com.designmodel.demo.Demo;

public abstract  class BaseService<Base> {

    public Base save(Base t){
        System.out.println("这是BaseService");
        saveOrUpdate(t);
        return t;
    }
    public abstract void saveOrUpdate(Base t);
}
