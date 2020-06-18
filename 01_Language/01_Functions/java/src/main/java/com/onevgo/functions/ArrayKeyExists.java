package com.onevgo.functions;

import java.util.HashMap;
import java.util.Map;

public class ArrayKeyExists {
    public static <K, V> boolean arrayKeyExists(K key, Map<K, V> map) {
        return map.containsKey(key);
    }

    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        map.put("first", 1);
        map.put("second", 4);
        map.put("", false);
        System.out.println(arrayKeyExists("first", map));
    }
}
