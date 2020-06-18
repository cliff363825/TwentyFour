package com.onevgo.functions;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArrayIntersectKey {
    public static <K, V> Map<K, V> arrayIntersectKey(Map<K, V> map, Map<K, V>... mapArgs) {
        Map<K, V> res = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            for (int i = 0; i < mapArgs.length; i++) {
                if (!mapArgs[i].containsKey(entry.getKey())) {
                    break;
                }
                if (i == mapArgs.length - 1) {
                    res.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        Map<Object, Object> map1 = new LinkedHashMap<>();
        map1.put("blue", 1);
        map1.put("red", 2);
        map1.put("green", 3);
        map1.put("purple", 4);

        Map<Object, Object> map2 = new LinkedHashMap<>();
        map2.put("green", 5);
        map2.put("blue", 6);
        map2.put("yellow", 7);
        map2.put("cyan", 8);

        System.out.println(arrayIntersectKey(map1, map2));
    }
}
