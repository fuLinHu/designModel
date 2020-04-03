package com.study.javamodel.juc.base.guardeobj.Three;

import java.util.Set;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 10:13
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<Integer> guardeIds = EmailBox.getGuardeIds();
        for (Integer guardeId : guardeIds) {
            new PostMan(guardeId,"这是客户"+guardeId+"的信件！").start();

        }
    }
}
