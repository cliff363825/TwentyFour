package com.onevgo.functions;

import com.google.common.collect.ImmutableList;

import java.util.*;
import java.util.function.Supplier;

public class ArrayChunk {
    public static <E> List<List<E>> arrayChunk(List<E> input, int size) {
        int capacity = (int) Math.ceil(input.size() * 1.0 / size);
        List<List<E>> result = new ArrayList<>(capacity);
        List<E> chunk = null;
        for (E e : input) {
            if (chunk == null || chunk.size() == size) {
                chunk = new ArrayList<>(size);
                result.add(chunk);
            }
            chunk.add(e);
        }
        return result;
    }

    public static <K, V> List<Map<K, V>> arrayChunk(Map<K, V> input, int size, Supplier<Map<K, V>> supplier) {
        int capacity = (int) Math.ceil(input.size() * 1.0 / size);
        List<Map<K, V>> result = new ArrayList<>(capacity);
        Map<K, V> chunk = null;
        for (Map.Entry<K, V> entry : input.entrySet()) {
            if (chunk == null || chunk.size() == size) {
                chunk = supplier.get();
                result.add(chunk);
            }
            chunk.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = ImmutableList.of("a", "b", "c", "d", "e");
        System.out.println(arrayChunk(list, 2)); // [[a, b], [c, d], [e]]

        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");
        map.put("k5", "v5");
        System.out.println(arrayChunk(map, 2, LinkedHashMap::new));
    }
}
