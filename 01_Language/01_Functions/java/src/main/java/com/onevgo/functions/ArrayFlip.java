package com.onevgo.functions;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayFlip {
    public static <K> Map<K, Integer> arrayFlip(Iterable<K> array) {
        Map<K, Integer> map = new LinkedHashMap<>();
        int value = 0;
        for (K key : array) {
            map.put(key, value);
            value++;
        }
        return map;
    }

    public static <K, V> Map<V, K> arrayFlip(Map<K, V> array) {
        return array.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (o, o2) -> o2, LinkedHashMap::new)
        );
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("oranges", "apples", "pears");
        System.out.println(arrayFlip(list));

        Map<Object, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 2);
        System.out.println(arrayFlip(map));
    }
}
