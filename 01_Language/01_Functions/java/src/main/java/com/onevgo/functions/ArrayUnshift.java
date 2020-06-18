package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUnshift {
    public static <E> int arrayUnshift(List<E> list, E... args) {
        list.addAll(0, Arrays.asList(args));
        return list.size();
    }

    public static void main(String[] args) {
        ArrayList<String> queue = new ArrayList<>(Arrays.asList("orange", "banana"));
        arrayUnshift(queue, "apple", "raspberry");
        System.out.println(queue);
    }
}
