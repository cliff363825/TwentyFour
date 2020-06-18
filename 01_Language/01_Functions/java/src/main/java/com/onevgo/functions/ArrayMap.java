package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayMap {
    public static <E, R> List<R> arrayMap(Function<E, R> function, List<E> list) {
        return list.stream().map(function).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(arrayMap(e -> e * e * e, list));
        System.out.println(list);
    }
}
