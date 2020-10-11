package com.onevgo.functions;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InArray {
    public static boolean inArray(Object needle, Collection haystack) {
        return haystack.contains(needle);
    }

    public static boolean inArray(Object needle, Map haystack) {
        return haystack.containsValue(needle);
    }

    public static void main(String[] args) {
        List<String> os = Lists.newArrayList("Mac", "NT", "Irix", "Linux");
        System.out.println(inArray("Irix", os));
        System.out.println(inArray("mac", os));
    }
}
