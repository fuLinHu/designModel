package com.study.javamodel.javadesignmodel.strategy;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 10:49
 * @Version V1.0
 */
public class Test {
    public static void main(String[] args) {
        DbContext context = new DbContext(new Mysql());
        context.executeDb();
        context = new DbContext(new Orcal());
        context.executeDb();
        context = new DbContext(new SqlService());
        context.executeDb();
    }
}
