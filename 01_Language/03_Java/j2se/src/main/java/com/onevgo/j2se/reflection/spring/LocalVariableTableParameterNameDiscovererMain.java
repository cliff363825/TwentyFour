package com.onevgo.j2se.reflection.spring;

import com.onevgo.j2se.reflection.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class LocalVariableTableParameterNameDiscovererMain {
    public static void main(String[] args) {
        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

        Method[] declaredMethods = Example.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            log.info("{}={}", method.getName(), Arrays.toString(parameterNames));
        }
    }
}
