package com.onevgo.j2se.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaMain {
    public static void main(String[] args) {
        String s = testLambda(String::toUpperCase, "abc");
        log.info("{}", s);
    }

    private static String testLambda(ExampleLambda<String> func, String str) {
        return func.getValue(str);
    }
}
