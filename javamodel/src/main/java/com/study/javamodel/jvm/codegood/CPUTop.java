package com.study.javamodel.jvm.codegood;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/5 15:35
 * @Version V1.0
 */
public class CPUTop {
    public static final int initData = 666;
    public static User user = new User();
    public int compute() {
        //一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }
    public static void main(String[] args) {
        CPUTop math = new CPUTop();
        while (true){
            math.compute();
        }
    }
}
