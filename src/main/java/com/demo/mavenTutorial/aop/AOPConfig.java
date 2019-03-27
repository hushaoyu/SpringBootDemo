package com.demo.mavenTutorial.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class AOPConfig {
    @Around("target(com.demo.mavenTutorial.service.SayHelloService)")
    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();
            System.out.println("args: " + Arrays.asList(args));

            // 调用原有的方法
            Object o = pjp.proceed();
            System.out.println("return: " + o);
            return o;
        } catch (Throwable e) {
            throw e;
        }
    }
}
