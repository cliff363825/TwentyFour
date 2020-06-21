package com.onevgo.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 把这个类声明为一个切面：需要把该类放入到 IOC 容器中、在声明为一个切面
 * <code>@Aspect</code>: 这个类是一个切面
 */
@Order(2)
@Aspect
@Component
public class LoggingAspect {

    /**
     * 定义一个方法，用于声明切入点表达式。一般地，该方法中不再需要添入其他的代码
     * 使用 @Pointcut 来声明切入点表达式
     * 后面的其他通知直接使用方法名来引用当前的切面表达式
     */
    @Pointcut("execution(public int com.onevgo.spring.aop.annotation.ArithmeticCalculator.*(..))")
    public void declareJoinPointExpression() {
    }

    /**
     * 声明该方法是一个前置通知：在目标方法开始之前执行
     * <code>@Before</code>: 这个方法是一个前置通知
     * <code>@Before("execution(* com.onevgo.spring.aop.impl..*.*(..))")</code>: 通知内容是切点表达式
     *
     * @param joinPoint 这个是连接点
     */
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.beforeMethod: " + methodName + "(" + args + ")");
    }

    /**
     * 后置通知： 在目标方法执行后（无论是否发生异常），执行的通知
     * 在后置通知中还不能访问目标方法执行的结果。
     *
     * @param joinPoint
     */
    @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.afterMethod: " + methodName + "(" + args + ")");
    }

    /**
     * 在方法正常结束时执行的代码
     * 返回通知时可以访问到方法的返回值的
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "declareJoinPointExpression()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.afterReturning: " + methodName + "(" + args + ") => " + result);
    }

    /**
     * 在目标方法出现异常时会执行的代码
     * 可以访问到异常对象，且可以指定在出现特定异常时执行通知代码
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "declareJoinPointExpression()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("LoggingAspect.afterThrowing: " + methodName + "(" + args + ") => " + ex);
    }

    /**
     * 环绕通知需要携带 ProceedingJoinPoint 类型的参数
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint 类型的参数可以决定是否执行目标方法。
     * 切环绕通知必须有返回值，返回值即为目标方法的返回值
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("declareJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        // 执行目标方法
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(proceedingJoinPoint.getArgs());

        try {
            // 前置通知
            System.out.println("LoggingAspect.aroundMethod: " + "@Before => " + methodName + "(" + args + ")");
            try {
                result = proceedingJoinPoint.proceed();
            } finally {
                // 后置通知
                System.out.println("LoggingAspect.aroundMethod: " + "@After => " + methodName + "(" + args + ")");
            }
            // 返回通知
            System.out.println("LoggingAspect.aroundMethod: " + "@AfterReturning => " + methodName + "(" + args + ") => " + result);
        } catch (Throwable e) {
            // 异常通知
            System.out.println("LoggingAspect.aroundMethod: " + "@AfterThrowing => " + methodName + "(" + args + ") => " + e);
        }
        // 后置通知
        return 100;
    }

    @DeclareParents(value = "com.onevgo.spring.aop.annotation.ArithmeticCalculator*", defaultImpl = MaxCalculatorImpl.class)
    private MaxCalculator maxCalculator;
    @DeclareParents(value = "com.onevgo.spring.aop.annotation.ArithmeticCalculator*", defaultImpl = MinCalculatorImpl.class)
    private MinCalculator minCalculator;
}
