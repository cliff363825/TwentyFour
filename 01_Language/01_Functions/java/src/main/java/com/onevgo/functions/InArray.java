package com.onevgo.functions;

import java.util.Arrays;
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
        List<String> os = Arrays.asList("Mac", "NT", "Irix", "Linux");
        System.out.println(inArray("Irix", os));
        System.out.println(inArray("mac", os));
    }
}
