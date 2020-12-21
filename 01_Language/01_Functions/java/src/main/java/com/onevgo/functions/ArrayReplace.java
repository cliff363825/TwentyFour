package com.onevgo.functions;

import java.util.HashMap;
import java.util.Map;

public class ArrayReplace {
    @SafeVarargs
    public static <K, V> Map<K, V> arrayReplace(Map<K, V> map, Map<K, V>... mapArgs) {
        Map<K, V> res = new HashMap<>(map);
        for (Map<K, V> m : mapArgs) {
            res.putAll(m);
        }
        return res;
    }

    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        map.put(0, "orange");
        map.put(1, "banana");
        map.put(2, "apple");
        map.put(3, "raspberry");

        Map<Object, Object> map1 = new HashMap<>();
        map1.put(0, "pineapple");
        map1.put(4, "cherry");

        Map<Object, Object> map2 = new HashMap<>();
        map2.put(0, "grape");

        System.out.println(arrayReplace(map, map1, map2));
    }
}
