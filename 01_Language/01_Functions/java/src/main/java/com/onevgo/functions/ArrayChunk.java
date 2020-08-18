package com.onevgo.functions;

import java.util.*;
import java.util.function.Supplier;

public class ArrayChunk {
    public static <E> List<List<E>> arrayChunk(Collection<E> col, int size) {
        int cap = (int) Math.ceil(col.size() * 1.0 / size);
        List<List<E>> res = new ArrayList<>(cap);
        List<E> l = null;
        for (E e : col) {
            if (l == null || l.size() == size) {
                l = new ArrayList<>(size);
                res.add(l);
            }
            l.add(e);
        }
        return res;
    }

    public static <K, V> List<Map<K, V>> arrayChunk(Map<K, V> map, int size, Supplier<Map<K, V>> supplier) {
        int cap = (int) Math.ceil(map.size() * 1.0 / size);
        List<Map<K, V>> res = new ArrayList<>(cap);
        Map<K, V> m = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (m == null || m.size() == size) {
                m = supplier.get();
                res.add(m);
            }
            m.put(entry.getKey(), entry.getValue());
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println(arrayChunk(list, 2)); // [[a, b], [c, d], [e]]

        HashMap<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");
        map.put("k5", "v5");
        System.out.println(arrayChunk(map, 2, LinkedHashMap::new));
    }
}
