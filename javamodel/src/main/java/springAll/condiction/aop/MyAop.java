package springAll.condiction.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/20 17:11
 * @Version V1.0
 */
@Aspect
@Component
@Slf4j
public class MyAop {
    //@Pointcut("execution(* com.study.javamodel.aop.service.impl..*.*(..))")
    @Pointcut("execution(* springAll.condiction.aop.MyAopEntity..*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("Before"+args);
    }
    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("after"+args);
    }
    @Around("pointCut()")
    public void around(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("around"+args);
    }

}
