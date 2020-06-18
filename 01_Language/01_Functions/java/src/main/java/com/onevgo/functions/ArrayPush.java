package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPush {
    public static <E> int arrayPush(List<E> list, E... args) {
        list.addAll(Arrays.asList(args));
        return list.size();
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("orange", "banana"));
        System.out.println(arrayPush(list, "apple", "raspberry"));
        System.out.println(list);
    }
}
