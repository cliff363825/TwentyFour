package com.onevgo.functions;

import cn.hutool.core.collection.IterUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArrayCombine {
    public static <K, V> Map<K, V> arrayCombine(Iterable<K> keys, Iterable<V> values) {
        return IterUtil.toMap(keys, values, true);
    }

    public static <K, V> Map<K, V> arrayCombine(K[] keys, V[] values) {
        return IterUtil.toMap(Arrays.asList(keys), Arrays.asList(values), true);
    }

    public static void main(String[] args) {
        List<String> a = Arrays.asList("green", "red", "yellow");
        List<String> b = Arrays.asList("avocado", "apple", "banana");
        Map<String, String> map = arrayCombine(a, b);
        System.out.println(map);
    }
}
