package com.onevgo.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ArrayMerge {
    public static <K, V> Map<K, V> arrayMerge(Map<K, V>... mapArgs) {
        return Stream.of(mapArgs).reduce(new HashMap<>(), (map, map2) -> {
            map.putAll(map2);
            return map;
        });
    }

    public static <E> List<E> arrayMerge(List<E>... listArgs) {
        return Stream.of(listArgs).reduce(new ArrayList<>(), (list, list2) -> {
            list.addAll(list2);
            return list;
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
