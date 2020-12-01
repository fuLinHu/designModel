package springAll.springsourcecode;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/17 8:33
 * @Version V1.0
 */
@Component
@Conditional(ConditionAn.class)
public class Car {
    private String carName;
    private Double price;
}
