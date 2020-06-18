package com.onevgo.functions;

import java.util.*;

public class DebugBacktrace {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("DebugBacktrace.test");
        List<StackTraceElement> stackTraceElements = Arrays.asList(Thread.currentThread().getStackTrace());
        System.out.println(stackTraceElements.subList(2, stackTraceElements.size()));
    }
}
