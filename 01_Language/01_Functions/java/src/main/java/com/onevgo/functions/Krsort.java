package com.onevgo.functions;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

import java.util.Comparator;

public class Krsort {
    public static void main(String[] args) {
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder()
                .put("d", "lemon")
                .put("a", "orange")
                .put("b", "banana")
                .put("c", "apple")
                .build();

        ImmutableSortedMap<String, String> map1 = ImmutableSortedMap.copyOf(map, Comparator.reverseOrder());
        System.out.println(map1);
    }
}
