package com.study.javamodel.javadesignmodel.singlmodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/17 9:37
 * @Version V1.0
 */
public class SingleInstanceErhan2 {

    private SingleInstanceErhan2(){

    }
    private static SingleInstanceErhan2 singleInstance= null;

    static {
        singleInstance = new SingleInstanceErhan2();
    }

    public static SingleInstanceErhan2 getInstance(){
        return singleInstance;
    }

    public static void main(String[] args) {
        System.out.println(SingleInstanceErhan2.getInstance()== SingleInstanceErhan2.getInstance());

    }


}
