package com.study.javamodel.javadesignmodel.strategy;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 10:47
 * @Version V1.0
 */

public class DbContext {
    private Db  db= null;
    public DbContext(Db  db){
        this.db=db;
    }
    public void executeDb(){
        db.doSql("select * from table");
    }

}
