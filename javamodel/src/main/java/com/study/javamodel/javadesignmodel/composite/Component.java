package com.study.javamodel.javadesignmodel.composite;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/13 16:57
 * @Version V1.0
 */
public abstract class Component {
    private String name;
    public Component(String name){
        this.name=name;
    }

    public abstract void add(Component c);

    public abstract Component remove(Component c);

    public abstract void display(int depth);
}
