package com.study.javamodel.javadesignmodel.strategy;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 10:43
 * @Version V1.0
 */
public class Orcal implements Db {
    @Override
    public void doSql(String sql) {
        System.out.println("orcal 查询数据。。。。：："+sql);
    }

}
