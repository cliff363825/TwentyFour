package com.onevgo.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ArrayWalk {
    public static <K, V> boolean arrayWalk(Map<K, V> map, Consumer<Map.Entry<K, V>> callback) {
        map.entrySet().forEach(callback);
        return true;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("d", "lemon");
        map.put("a", "orange");
        map.put("b", "banana");
        map.put("c", "apple");

        arrayWalk(map, e -> System.out.println(e.getKey() + ":" + e.getValue()));
        arrayWalk(map, e -> e.setValue("fruit:" + e.getValue()));
        arrayWalk(map, e -> System.out.println(e.getKey() + ":" + e.getValue()));
    }
}
