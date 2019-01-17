package com.designmodel.aop.config;

import com.designmodel.aop.entity.Test;
import com.designmodel.aop.service.TestValiService;
import com.designmodel.aop.service.impl.TestServiceImpl;
import com.designmodel.aop.service.impl.TestValiServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {


    @Pointcut("execution(* com.designmodel.aop.service.impl..*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String kind = joinPoint.getKind();
        Signature signature = joinPoint.getSignature();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        System.out.println("before");
    }
    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("after");
    }
    @AfterReturning(value = "pointCut()",returning = "keys")
    public void afterReturning(JoinPoint joinPoint, Object keys){
        System.out.println("afterReturning");
    }
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("around before");
       // jp.proceed();
        Object tst = new Test();
        ((Test) tst).setAge(100);
        ((Test) tst).setName("fu lin hu chinese");
        Object[] ob= new Object[1];
        ob[0]=tst;
        jp.proceed(ob);
        System.out.println("around after");
    }

    /*@DeclareParents(value = "com.designmodel.aop.service.impl.TestServiceImpl+",defaultImpl = TestValiServiceImpl.class)
    public TestValiService testValiService;*/

}
