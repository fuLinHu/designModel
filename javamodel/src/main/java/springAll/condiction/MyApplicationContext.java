package springAll.condiction;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public static <T> T getBeanByName(String beanName){
        T t = (T)applicationContext.getBean(beanName);
        return t;
    }
    public static <T> T getBeanByType(Class<T> beanType){
        T t = applicationContext.getBean(beanType);
        return t;
    }
}
