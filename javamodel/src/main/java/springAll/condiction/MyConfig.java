package springAll.condiction;

import commit.entity.Student;
import commit.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import springAll.condiction.MyCondiction;
import springAll.condiction.aop.MyAopEntity.FlhCode;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/17 15:28
 * @Version V1.0
 */
@Configuration
@Slf4j
@EnableAspectJAutoProxy
@ComponentScan({"springAll.condiction"})
public class MyConfig {
    @Bean("user")
    public User getUser(){
        log.info("构建user");
        return new User("小王",20,new Date(),175);
    }
    @Conditional(MyCondiction.class)
    @Bean("student")
    public User getStudent(){
        log.info("构建user");
        return new Student();
    }
    @Bean("flhCode")
    public FlhCode getFlhCode(){
        return new FlhCode();
    }
}
