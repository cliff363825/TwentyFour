package com.onevgo.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(1)
@Aspect
@Component
public class ValidateAspect {

    @Before("com.onevgo.spring.aop.annotation.LoggingAspect.declareJoinPointExpression()")
    public void validateArgs(JoinPoint joinPoint) {
        String signatureName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("ValidateAspect.validateArgs: " + signatureName + "(" + args + ")");
    }
}
