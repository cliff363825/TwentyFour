package com.onevgo.spring.aop.xml;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.List;

public class LoggingAspect {

    public void declareJoinPointExpression() {
    }

    /**
     *
     * @param joinPoint 这个是连接点
     */
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.beforeMethod: " + methodName + "(" + args + ")");
    }

    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.afterMethod: " + methodName + "(" + args + ")");
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.afterReturning: " + methodName + "(" + args + ") => " + result);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.afterThrowing: " + methodName + "(" + args + ") => " + ex);
    }
}
