package com.onevgo.functions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Next {
    public static void main(String[] args) {
        List<String> transport = Arrays.asList("foot", "bike", "car", "plane");
        Iterator<String> iterator = transport.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }
}
