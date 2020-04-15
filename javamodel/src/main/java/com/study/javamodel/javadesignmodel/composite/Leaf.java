package com.study.javamodel.javadesignmodel.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/13 17:24
 * @Version V1.0
 */
@Slf4j
public class Leaf extends Component {
    private String name;
    public Leaf(String name) {
        super(name);
        this.name= name;
    }

    @Override
    public void add(Component c) {
        log.info("叶子节点，不进行添加操作");
    }

    @Override
    public Component remove(Component c) {
        log.info("叶子节点，不进行删除操作");
        return null;
    }

    @Override
    public void display(int depth) {
        String str = "";
        for (int i=0; i<depth; i++){
            str = str + "-";
        }
        System.out.println(str + name);
    }
}
