package tes.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class Teacher {
    private String name;
    private int age;
    public Teacher(){
        log.info("调用构造器");
    }

    public void teInit(){
        log.info("初始化化");
    }

    public void teDestory(){
        log.info("方法销毁");
    }
}
