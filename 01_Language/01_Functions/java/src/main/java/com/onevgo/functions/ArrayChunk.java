package com.onevgo.functions;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;

public class ArrayChunk {
    public static <E> List<List<E>> arrayChunk(Collection<E> input, int size) {
        return CollectionUtil.split(input, size);
    }

    public static <K, V> List<Map<K, V>> arrayChunk(Map<K, V> input, int size) {
        return arrayChunk(input.entrySet(), size).stream().collect(ArrayList::new, (l, entries) -> {
            Map<K, V> m = new LinkedHashMap<>(entries.size());
            for (Map.Entry<K, V> entry : entries) {
                m.put(entry.getKey(), entry.getValue());
            }
            l.add(m);
        }, List::addAll);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println(arrayChunk(list, 2)); // [[a, b], [c, d], [e]]

        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");
        map.put("k5", "v5");
        System.out.println(arrayChunk(map, 2));
    }
}
