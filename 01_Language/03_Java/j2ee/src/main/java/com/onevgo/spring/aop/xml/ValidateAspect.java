package com.onevgo.spring.aop.xml;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.List;

public class ValidateAspect {

    public void validateArgs(JoinPoint joinPoint) {
        String signatureName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("ValidateAspect.validateArgs: " + signatureName + "(" + args + ")");
    }
}
