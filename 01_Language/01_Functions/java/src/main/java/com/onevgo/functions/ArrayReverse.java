package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayReverse {
    public static <E> List<E> arrayReverse(List<E> list) {
        List<E> res = new ArrayList<>(list);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        List<Object> input = Arrays.asList("php", 4.0, Arrays.asList("green", "red"));
        System.out.println(arrayReverse(input));
    }
}
