package tes.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(TeacherConfig.class);
        log.info("初始化容器");
        ap.close();
    }
}
