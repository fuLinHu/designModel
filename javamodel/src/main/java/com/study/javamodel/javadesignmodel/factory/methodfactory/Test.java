package com.study.javamodel.javadesignmodel.factory.methodfactory;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:00
 * @Version V1.0
 */
public class Test {
    //为每种车  创建一个工厂
    public static void main(String[] args) {
        Factory factory = new AoDiFactory();
        Car factory1 = factory.createFactory();
        factory1.run();

        factory  = new HongQiFactory();
        factory1 =factory.createFactory();
        factory1.run();

        factory  = new DaZhongFactory();
        factory1 =factory.createFactory();
        factory1.run();

        //如果加新的 car  不用修改原有代码即可
        factory  = new NewCarFactory();
        factory1 =factory.createFactory();
        factory1.run();


    }
}
