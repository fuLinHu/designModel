package com.study.javamodel.jvm.codegood;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/5 22:59
 * @Version V1.0
 */
public class InternTest {
    public static void main(String[] args) {
        //String str2 = new StringBuilder("计算机").append("技术").toString();
        String str1 =  new StringBuilder().append("计算机").append("技术").toString();
        System.out.println(str1 == str1.intern()); //true
        String str2 =  new StringBuilder("123").append("计算机").append("技术").toString();
        System.out.println(str2 == str2.intern()); //true
        String str3 =  new StringBuilder().append("计算机").toString();
        System.out.println(str3 == str3.intern()); //false
        String str4 =  new StringBuilder().append("计算机").append("1234").toString();
        System.out.println(str4 == str4.intern()); //true
        String str5 =  new StringBuilder().append("计算1234").toString();
        System.out.println(str5 == str5.intern());  //false







        //String str2 = new StringBuilder("计算机").toString();
        //没有出现"计算机技术"字面量，所以不会在常量池里生 成"计算机技术"对象 4
        //System.out.println(str2 == str2.intern()); //true
    }
}
