package com.study.javamodel.javadesignmodel.proxy.cglib;

import com.study.javamodel.javadesignmodel.proxy.dynamicproxy.UserService;
import commit.entity.User;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 16:06
 * @Version V1.0
 */
@Slf4j
public class UserServiceImpl  {
    public void add(Object obj) {
        log.info("添加的对象为："+JSONObject.fromObject(obj).toString());
    }

    public void delete(String id) {
        log.info("删除的主键为："+id);
    }

    public void updte(Object obj) {
        log.info("修改的对象为："+JSONObject.fromObject(obj).toString());
    }

    public Object select() {
        log.info("查询的结果：");
        return new User("付林虎",20,new Date(),183.5);
    }
}
