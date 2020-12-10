package com.onevgo.functions;

import java.util.*;
import java.util.stream.Stream;

public class ArrayMerge {
    @SafeVarargs
    public static <K, V> Map<K, V> arrayMerge(Map<K, V> array1, Map<K, V> array2, Map<K, V>... arrays) {
        Stream.Builder<Map<K, V>> builder = Stream.builder();
        builder.add(array1).add(array2);
        for (Map<K, V> array : arrays) {
            builder.add(array);
        }
        return builder.build().reduce(new LinkedHashMap<>(), (m1, m2) -> {
            m1.putAll(m2);
            return m1;
        });
    }

    @SafeVarargs
    public static <E> List<E> arrayMerge(List<E> array1, List<E> array2, List<E>... arrays) {
        Stream.Builder<List<E>> builder = Stream.builder();
        builder.add(array1).add(array2);
        for (List<E> array : arrays) {
            builder.add(array);
        }
        return builder.build().reduce(new ArrayList<>(), (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });
    }

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("color", "red");

        HashMap<Object, Object> map1 = new HashMap<>();
        map.put("color", "green");
        map.put("shape", "trapezoid");

        System.out.println(arrayMerge(map, map1));
    }
}
