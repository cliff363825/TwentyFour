package com.onevgo.functions;

import com.google.common.collect.ImmutableSortedMap;

import java.util.HashMap;

public class Ksort {
    public static void main(String[] args) {
        HashMap<String, String> fruits = new HashMap<>();
        fruits.put("d", "lemon");
        fruits.put("a", "orange");
        fruits.put("b", "banana");
        fruits.put("c", "apple");

        ImmutableSortedMap<String, String> map = ImmutableSortedMap.copyOf(fruits);
        System.out.println(map);
    }
}
