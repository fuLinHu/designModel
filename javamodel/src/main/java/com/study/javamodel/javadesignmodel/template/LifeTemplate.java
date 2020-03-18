package com.study.javamodel.javadesignmodel.template;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 10:07
 * @Version V1.0
 */
public abstract class LifeTemplate {
    public abstract  void born();
    public abstract  void grow();
    public abstract  void die();
    //模板
    public final void myLife(){
        born();
        grow();
        die();
    }
}
