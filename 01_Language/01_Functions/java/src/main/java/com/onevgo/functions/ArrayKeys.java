package com.onevgo.functions;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayKeys {
    public static <K, V> List<K> arrayKeys(Map<K, V> map) {
        return new ArrayList<>(map.keySet());
    }

    public static <E> List<Integer> arrayKeys(List<E> list) {
        List<Integer> res = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            res.add(i);
        }
        return res;
    }

    public static <K, V> List<K> arrayKeys(Map<K, V> map, V search) {
        return map.keySet().stream().filter(k -> {
            V v = map.get(k);
            return Objects.equals(search, v);
        }).collect(Collectors.toList());
    }

    public static <E> List<Integer> arrayKeys(List<E> list, E search) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            E e = list.get(i);
            if (Objects.equals(search, e)) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<Object, Object> map = new LinkedHashMap<>();
        map.put(0, 100);
        map.put("color", "red");
        System.out.println(arrayKeys(map));

        List<String> list = Arrays.asList("blue", "red", "green", "blue", "blue");
        System.out.println(arrayKeys(list, "blue"));
    }
}
