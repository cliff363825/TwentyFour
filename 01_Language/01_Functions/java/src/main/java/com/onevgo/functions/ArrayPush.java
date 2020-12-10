package com.onevgo.functions;

import java.util.*;

public class ArrayPush {
    @SafeVarargs
    public static <E> int arrayPush(Collection<E> list, E... args) {
        Collections.addAll(list, args);
        return list.size();
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("orange", "banana"));
        System.out.println(arrayPush(list, "apple", "raspberry"));
        System.out.println(list);
    }
}
