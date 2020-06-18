package com.onevgo.functions;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ArrayCombine {
    public static <K, V> Map<K, V> arrayCombine(List<K> keys, List<V> values, Supplier<Map<K, V>> supplier) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException();
        }

        Map<K, V> map = supplier.get();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    public static <K, V> Map<K, V> arrayCombine(K[] keys, V[] values, Supplier<Map<K, V>> supplier) {
        if (keys.length != values.length) {
            throw new IllegalArgumentException();
        }

        Map<K, V> map = supplier.get();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public static void main(String[] args) {
        List<String> a = Arrays.asList("green", "red", "yellow");
        List<String> b = Arrays.asList("avocado", "apple", "banana");
        Map<String, String> map = arrayCombine(a, b, LinkedHashMap::new);
        System.out.println(map);
    }
}
