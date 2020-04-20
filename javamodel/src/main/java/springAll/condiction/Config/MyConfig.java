package springAll.condiction.Config;

import commit.entity.Student;
import commit.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springAll.condiction.MyCondiction;

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
}
