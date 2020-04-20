package springAll.condiction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springAll.condiction.aop.MyAopEntity.FlhCode;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/17 15:28
 * @Version V1.0
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for (String name:beanDefinitionNames) {
            log.info("通过注解获取的bean:"+name);
        }
        FlhCode flhCode = (FlhCode)app.getBean("flhCode");
        flhCode.flhRun("flh");


    }
}
