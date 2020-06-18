package com.onevgo.functions;

import java.util.Arrays;
import java.util.function.Function;

public class CallUserFunc {
    public static <T, R> R callUserFunc(Function<T[], R> function, T... t) {
        return function.apply(t);
    }

    public static void main(String[] args) {
        String s = callUserFunc(t -> {
            System.out.println(Arrays.toString(t));
            return String.join(",", t);
        }, "a", "b", "c");
        System.out.println(s);
    }
}
