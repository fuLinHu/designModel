package com.study.javamodel.javadesignmodel.template;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 10:10
 * @Version V1.0
 */
public class XiaoHong extends LifeTemplate{
    @Override
    public void born() {
        System.out.println("小红出生了");
    }

    @Override
    public void grow() {
        System.out.println("小红长大了");
    }

    @Override
    public void die() {
        System.out.println("小红长死亡了");
    }
}
