package com.onevgo.functions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Asort {
    public static <K, V extends Comparable<V>> Map<K, V> asort(Map<K, V> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("d", "lemon");
        map.put("a", "orange");
        map.put("b", "banana");
        map.put("c", "apple");
        Map<String, String> asort = asort(map);
        System.out.println(asort);
    }
}
