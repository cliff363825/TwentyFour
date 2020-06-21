package com.onevgo.spring.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * <code>@Aspect</code> 告诉Spring当前类是一个切面类
 */
@Aspect
public class LogAspects {
    /**
     * 抽取公共的切入点表达式
     * 1. 本类引用
     * 2. 其他的切面引用
     */
    @Pointcut("execution(public int com.onevgo.spring.annotation.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    /**
     * <code>@Before</code> 在目标方法之前切入，切入点表达式（指定在哪个方法切入）
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName() + "运行。。。参数列表是：{" + Arrays.asList(args) + "}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "结束。。。");
    }

    /**
     * JoinPoint 一定要出现在参数表的第一位
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "正常返回。。。运行结果：{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "异常。。。异常信息：{" + exception + "}");
    }
}
