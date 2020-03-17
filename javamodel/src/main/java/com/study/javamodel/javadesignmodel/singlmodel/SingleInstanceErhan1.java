package com.study.javamodel.javadesignmodel.singlmodel;

import sun.awt.geom.AreaOp;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/17 9:37
 * @Version V1.0
 */
public class SingleInstanceErhan1 {

    private SingleInstanceErhan1(){

    }
    private static SingleInstanceErhan1  singleInstance= new SingleInstanceErhan1();

    public static SingleInstanceErhan1 getInstance(){
        return singleInstance;
    }

    public static void main(String[] args) {
        System.out.println(SingleInstanceErhan1.getInstance()==SingleInstanceErhan1.getInstance());

    }


}
