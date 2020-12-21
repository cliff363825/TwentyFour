package com.onevgo.functions;

import com.google.common.collect.ImmutableSortedMap;

import java.util.Comparator;
import java.util.HashMap;

public class Krsort {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("d", "lemon");
        map.put("a", "orange");
        map.put("b", "banana");
        map.put("c", "apple");

        ImmutableSortedMap<String, String> map1 = ImmutableSortedMap.copyOf(map, Comparator.reverseOrder());
        System.out.println(map1);
    }
}
