package com.onevgo.functions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayFillKeys {
    public static <K, V> Map<K, V> arrayFillKeys(Collection<K> keys, V value) {
        return keys.stream().collect(Collectors.toMap(Function.identity(), v -> value, (v, v2) -> v2, HashMap::new));
    }

    public static void main(String[] args) {
        List<Object> list = Arrays.asList("foo", 5, 10, "bar");
        System.out.println(arrayFillKeys(list, "banana"));
    }
}
