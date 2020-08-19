package com.study.javamodel.juc.base.compilerInstructioreordering;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/14 17:22
 * @Version V1.0
 */
public class CompilerInstructioReordering {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


    }
}
