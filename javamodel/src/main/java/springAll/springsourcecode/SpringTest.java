package springAll.springsourcecode;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/27 11:41
 * @Version V1.0
 */
public class SpringTest {
    public static void main(String[] args) {
        //Assert.notNull(null, "Bean name must not be null");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
//        Object student = applicationContext.getBean("student");
//        System.out.println(student);
    }
}
