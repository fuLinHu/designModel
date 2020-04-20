package springAll.condiction.aop.MyAopEntity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/20 17:26
 * @Version V1.0
 */
@Slf4j
@Component
public class FlhCode {

    public Double flhSum(Double n1,Double n2){
        return n1+n2;
    }
    public void flhRun(String name){
        log.info("无参数的运行"+name);
    }

}
