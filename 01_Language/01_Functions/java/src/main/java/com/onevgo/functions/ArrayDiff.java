package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayDiff {
    @SafeVarargs
    public static <E> List<E> arrayDiff(Collection<E> col, Collection<E>... colArgs) {
        List<E> res = new ArrayList<>();
        for (E e : col) {
            for (int i = 0; i < colArgs.length; i++) {
                if (colArgs[i].contains(e)) {
                    break;
                }
                if (i == colArgs.length - 1) {
                    res.add(e);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("green", "red", "blue", "red");
        List<String> list2 = Arrays.asList("green", "yellow", "red");
        System.out.println(arrayDiff(list1, list2));
    }
}
