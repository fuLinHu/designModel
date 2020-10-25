package com.study.javamodel.lamdba;

import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/4 9:30
 * @Version V1.0
 */
public class TestColon {
    public static void main(String[] args) {
        String s = "hello i am  a dog";
        System.out.println(s.toUpperCase());
        //============================
        BiFunction<String, Locale, String> stringLocaleStringBiFunction = String::toUpperCase;
        String apply = stringLocaleStringBiFunction.apply(s, Locale.CHINA);
        System.out.println();
        System.out.println(apply);

        Function<String,String> func =  String::toUpperCase;
        func.apply("---hello i am  a dog---");

        Consumer<TestColon> add = TestColon::add;
        add.accept(new TestColon());
        BiConsumer<TestColon, Integer> testColonIntegerBiConsumer = TestColon::add;
        testColonIntegerBiConsumer.accept(new TestColon(),3);
        Function<TestColon, String> testColonIntegerIntegerBiFunction = TestColon::addreturn;
        testColonIntegerIntegerBiFunction.apply(new TestColon());
        BiFunction<TestColon, Integer, Integer> testColonIntegerIntegerBiFunction1 = TestColon::addreturn;
        testColonIntegerIntegerBiFunction1.apply(new TestColon(),7);
    }

    public void add(){
        System.out.println("--------add--------");
    }
    public void add(int a){
        System.out.println("--------"+a+"的平方为：--------"+a*a);
    }

    public String  addreturn(){
        System.out.println("--------addreturn--------");
        return "返回addreturn";
    }
    public int addreturn(int a){
        System.out.println("--------"+a+"的平方为：--------"+a*a);
        return a*a;
    }

}
