package com.study.javamodel.javadesignmodel.CompositePatter;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/7 8:50
 * @Version V1.0
 */
public class Directory implements Node {
    private String name;

    public Directory(String name) {
        this.name = name;
    }

    private List<Node> childrent = new ArrayList<>();
    @Override
    public void printNodeName() {
        System.out.println(name);
    }

    public void addNode(Node node){
        childrent.add(node);
    }

    public List<Node> getChildrent() {
        return childrent;
    }
}
