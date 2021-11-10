package javatodb;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/10 9:58
 * @Version V1.0
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface JAVABeanToDB {
    String dbField() default "";  //数据库字段
    String tableName() default ""; //数据库表明
    boolean iftableId() default false; //数据库主键
    int length() default 255; //数据库表明
}
