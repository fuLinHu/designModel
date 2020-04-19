package tes.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan({"tes.bean"})
public class TeacherConfig {
    @Bean(initMethod = "teInit",destroyMethod = "teDestory")
    public Teacher getTeacher(){
        return new Teacher();
    }

}
