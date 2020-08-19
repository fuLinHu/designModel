package com.study.javamodel.javadesignmodel.CompositePatter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/7 8:50
 * @Version V1.0
 */
public class LeafNode implements Node {

    private String name;

    public LeafNode(String name) {
        this.name = name;
    }
    @Override
    public void printNodeName() {
        System.out.println(name);
    }
}
