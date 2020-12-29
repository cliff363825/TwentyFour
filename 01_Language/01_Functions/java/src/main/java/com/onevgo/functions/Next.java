package com.onevgo.functions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Next {
    public static <T> T next(Iterator<T> array) {
        return array.next();
    }

    public static void main(String[] args) {
        List<String> transport = Arrays.asList("foot", "bike", "car", "plane");
        Iterator<String> iterator = transport.listIterator();
        System.out.println(next(iterator));
        System.out.println(next(iterator));
        System.out.println(next(iterator));
        System.out.println(next(iterator));
    }
}
