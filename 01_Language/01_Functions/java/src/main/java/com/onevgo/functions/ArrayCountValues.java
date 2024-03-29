package com.onevgo.functions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayCountValues {
    public static <K> Map<K, Long> arrayCountValues(Collection<K> input) {
        return input.stream().collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
    }

    public static void main(String[] args) {
        List<Object> list = Arrays.asList(1, "hello", 1, "world", "hello");
        System.out.println(arrayCountValues(list));
    }
}
