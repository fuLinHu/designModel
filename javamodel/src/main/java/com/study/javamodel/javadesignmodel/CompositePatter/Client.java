package com.study.javamodel.javadesignmodel.CompositePatter;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/7 8:54
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Directory directory =new Directory("root");
        Directory ch1 =new Directory("ch1");
        Directory ch2 =new Directory("ch2");
        Directory ch3 =new Directory("ch3");
        directory.addNode(ch1);
        directory.addNode(ch2);
        directory.addNode(ch3);
        Directory ch11 =new Directory("ch11");
        Directory ch21 =new Directory("ch21");
        Directory ch31 =new Directory("ch31");
        ch1.addNode(ch11);
        ch2.addNode(ch21);
        ch3.addNode(ch31);
        LeafNode L1 =new LeafNode("L1");
        LeafNode L2 =new LeafNode("L2");
        LeafNode L3 =new LeafNode("L3");
        ch11.addNode(L1);
        ch11.addNode(L2);
        ch21.addNode(L3);
        tree(directory,1);



    }

    public static void tree(Node node,int depth){
        for(int i =0;i<depth;i++){
            System.out.print("--");
        }
        node.printNodeName();
        if(node instanceof Directory){
            Directory n = (Directory)node;
            List<Node> childrent = n.getChildrent();
            for (Node node1 : childrent) {
                tree(node1,depth+1);
            }
        }
    }
}
