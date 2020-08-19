package com.study.javamodel.javadesignmodel.bulider;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/17 15:32
 * @Version V1.0
 */
public class Test {
    public static void main(String[] args) {
        Person bulider = new Person.PersionBulider()
                .baseInfro("fulinhu", 30)
                .adress("河南", "中国")
                //.sex('男')
                .tall(181)
                .bulider();
        System.out.println(bulider.toString());
    }
}
