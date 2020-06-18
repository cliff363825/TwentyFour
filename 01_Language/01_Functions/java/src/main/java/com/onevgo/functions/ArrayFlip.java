package com.onevgo.functions;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayFlip {
    public static <K> Map<K, Integer> arrayFlip(List<K> list) {
        Map<K, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }
        return map;
    }

    public static <K, V> Map<V, K> arrayFlip(Map<K, V> map) {
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (o, o2) -> o2));
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
