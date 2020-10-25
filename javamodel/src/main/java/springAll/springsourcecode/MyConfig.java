package springAll.springsourcecode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/18 10:11
 * @Version V1.0
 */
@Configuration
public class MyConfig {
    @Bean
    public Student getStu(){
        return new Student();
    }
    @Bean
    public void run(){
        getStu();
    }
}
