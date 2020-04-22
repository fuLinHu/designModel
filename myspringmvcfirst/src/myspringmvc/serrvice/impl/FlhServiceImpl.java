package myspringmvc.serrvice.impl;


import myspringmvc.annotation.MyService;
import myspringmvc.serrvice.FlhService;


/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/21 16:25
 * @Version V1.0
 */
@MyService("flhservice1")
public class FlhServiceImpl implements FlhService {
    @Override
    public String  query(String age, String name) {
        System.out.println("查询的age：："+age+" name::"+name);
        return "查询的age：："+age+" name::"+name;
    }

    @Override
    public void insert(Object user) {
        System.out.println("插入成功----");
    }
}
