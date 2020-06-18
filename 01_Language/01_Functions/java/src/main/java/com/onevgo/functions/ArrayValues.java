package com.onevgo.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayValues {
    public static <K, V> List<V> arrayValues(Map<K, V> map) {
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("size", "XL");
        map.put("color", "gold");
        System.out.println(arrayValues(map));
    }
}
