package com.study.javamodel.javadesignmodel.template;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 10:14
 * @Version V1.0
 */
public class XiaoMing extends LifeTemplate {
    String a = "";
    @Override
    public void born() {
        a = "";
        System.out.println("小明出生了");

    }

    @Override
    public void grow() {
        a = "";
        System.out.println("小明长大了");
    }

    @Override
    public void die() {
        a = "";
        System.out.println("小明长死亡了");
    }
}
