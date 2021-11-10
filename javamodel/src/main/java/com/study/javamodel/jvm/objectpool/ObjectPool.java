package com.study.javamodel.jvm.objectpool;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/10/22 9:30
 * @Param $
 * @return $
 * @Version V1.0
 */
public class ObjectPool {

    public static void main(String[] args) {
        Integer i1 = -129;  //这种调用底层实际是执行的Integer.valueOf(127)，里面用到了IntegerCache对象池
        Integer i2 = -129;
        System.out.println(i1 == i2);//输出true

    }
}
