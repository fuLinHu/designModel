package com.designmodel.demo.Demo;

public class Demo {

    public static void main(String [] arg){
        TestServiceImpl te=new TestServiceImpl();
        te.save(new Test());
    }
}
