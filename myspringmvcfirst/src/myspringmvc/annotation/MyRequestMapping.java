package myspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/21 16:03
 * @Version V1.0
 */

@Target({ElementType.FIELD,ElementType.TYPE}) //只作用在字段 类
@Retention(RetentionPolicy.RUNTIME) //可以反射获取
@Documented //包含在java的doc中
@Inherited // 可以被继承
public @interface MyRequestMapping {
    String value() default "";
}
