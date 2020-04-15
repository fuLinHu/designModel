package com.study.javamodel.javadesignmodel.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/13 17:28
 * @Version V1.0
 */
public class Composite extends Component {
    private String name;
    public Composite(String name) {
        super(name);
        this.name = name;
    }
    private List<Component> children = new ArrayList<>();

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public Component remove(Component c) {
        children.remove(c);
        return c;
    }

    @Override
    public void display(int depth) {
        String str = "";
        for (int i=0; i<depth; i++){
            str = str + "-";
        }
        System.out.println(str + name);
        for (Component component : children){
            component.display(depth + 2);
        }
    }
}
