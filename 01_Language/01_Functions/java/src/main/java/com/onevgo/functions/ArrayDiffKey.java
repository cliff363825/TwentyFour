package com.onevgo.functions;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArrayDiffKey {
    @SafeVarargs
    public static <K, V> Map<K, V> arrayDiffKey(Map<K, V> map, Map<K, V>... maps) {
        Map<K, V> res = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            for (int i = 0; i < maps.length; i++) {
                if (maps[i].containsKey(entry.getKey())) {
                    break;
                }
                if (i == maps.length - 1) {
                    res.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<Object, Object> m1 = new LinkedHashMap<>();
        m1.put("blue", 1);
        m1.put("red", 2);
        m1.put("green", 3);
        m1.put("purple", 4);

        Map<Object, Object> m2 = new LinkedHashMap<>();
        m2.put("green", 1);
        m2.put("yellow", 2);
        m2.put("cyan", 3);
        System.out.println(arrayDiffKey(m1, m2));
    }
}
