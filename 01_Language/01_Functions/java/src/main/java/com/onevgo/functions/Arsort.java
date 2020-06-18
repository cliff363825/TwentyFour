package com.onevgo.functions;

import java.util.*;
import java.util.stream.Collectors;

public class Arsort {
    public static <K, V extends Comparable<V>> Map<K, V> arsort(Map<K, V> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v, v2) -> v2, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("d", "lemon");
        map.put("a", "orange");
        map.put("b", "banana");
        map.put("c", "apple");
        Map<String, String> arsort = arsort(map);
        System.out.println(arsort);
    }
}
