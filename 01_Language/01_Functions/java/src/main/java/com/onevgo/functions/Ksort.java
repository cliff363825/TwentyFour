package com.onevgo.functions;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

public class Ksort {
    public static void main(String[] args) {
        ImmutableMap<String, String> fruits = ImmutableMap.<String, String>builder()
                .put("d", "lemon")
                .put("a", "orange")
                .put("b", "banana")
                .put("c", "apple")
                .build();
        ImmutableSortedMap<String, String> map = ImmutableSortedMap.copyOf(fruits);
        System.out.println(map);
    }
}
