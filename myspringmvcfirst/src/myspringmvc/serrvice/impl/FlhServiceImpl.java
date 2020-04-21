package myspringmvc.serrvice.impl;

import commit.entity.User;
import lombok.extern.slf4j.Slf4j;
import springAll.myspringmvc.annotation.MyService;
import springAll.myspringmvc.serrvice.FlhService;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/21 16:25
 * @Version V1.0
 */
@MyService("flhservice1")
@Slf4j
public class FlhServiceImpl implements FlhService {
    @Override
    public String  query(String age, String name) {
        log.info("查询的age：："+age+" name::"+name);
        return "查询的age：："+age+" name::"+name;
    }

    @Override
    public void insert(User user) {
        log.info("插入成功----");
    }
}
