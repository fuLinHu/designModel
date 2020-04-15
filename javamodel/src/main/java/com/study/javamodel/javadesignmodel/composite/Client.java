package com.study.javamodel.javadesignmodel.composite;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/13 17:31
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Component root =new Composite("root");
        root.add(new Leaf("root leaf A"));
        root.add(new Leaf("root leaf B"));

        Composite composite_x = new Composite("Composite X");
        composite_x.add(new Leaf("root composite_x leaf AX"));
        composite_x.add(new Leaf("root composite_x leaf BX"));

        root.add(composite_x);

        root.display(1);

    }
}
