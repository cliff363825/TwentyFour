package com.onevgo.functions;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class IsIterable {
    public static boolean isIterable(Object value) {
        return value instanceof Iterable;
    }

    public static void main(String[] args) {
        System.out.println(isIterable(Lists.newArrayList(1, 2, 3)));
        System.out.println(isIterable(Iterators.forArray(1, 2, 3)));
        System.out.println(isIterable(1));
        System.out.println(isIterable(new Object()));
    }
}
