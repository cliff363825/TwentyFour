package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArraySearch {
    public static <E> int arraySearch(E value, List<E> list) {
        return list.indexOf(value);
    }

    public static <K, V> K arraySearch(V value, Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            V v = entry.getValue();
            if (v == value || (value != null && value.equals(v))) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("blue", "red", "green", "red");
        System.out.println(arraySearch("green", list));
        System.out.println(arraySearch("red", list));
    }
}
