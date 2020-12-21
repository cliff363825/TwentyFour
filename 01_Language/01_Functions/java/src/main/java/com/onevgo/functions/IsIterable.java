package com.onevgo.functions;

import java.util.Arrays;

public class IsIterable {
    public static boolean isIterable(Object value) {
        return value instanceof Iterable;
    }

    public static void main(String[] args) {
        System.out.println(isIterable(Arrays.asList(1, 2, 3)));
        System.out.println(isIterable(Arrays.asList(1, 2, 3).iterator()));
        System.out.println(isIterable(1));
        System.out.println(isIterable(new Object()));
    }
}
