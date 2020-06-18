package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySplice {
    public static <E> List<E> arraySplice(List<E> list, int offset) {
        List<E> res = new ArrayList<>();
        offset = calcOffset(list, offset);
        while (offset < list.size()) {
            res.add(list.remove(offset));
        }
        return res;
    }

    public static <E> List<E> arraySplice(List<E> list, int offset, int length) {
        List<E> res = new ArrayList<>();
        offset = calcOffset(list, offset);
        if (length < 0) {
            length = Math.max(list.size() + length - offset, 0); // [0, size)
        }
        while (length > 0 && offset < list.size()) {
            res.add(list.remove(offset));
            length--;
        }
        return res;
    }

    public static <E> List<E> arraySplice(List<E> list, int offset, int length, E replacement) {
        offset = calcOffset(list, offset);
        List<E> res = arraySplice(list, offset, length);
        list.add(offset, replacement);
        return res;
    }

    public static <E> List<E> arraySplice(List<E> list, int offset, int length, List<E> replacement) {
        offset = calcOffset(list, offset);
        List<E> res = arraySplice(list, offset, length);
        list.addAll(offset, replacement);
        return res;
    }

    private static <E> int calcOffset(List<E> list, int offset) {
        if (offset < 0) {
            offset = Math.max(list.size() + offset, 0); // [0, size)
        }
        return offset;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("red", "green", "blue", "yellow"));
//        System.out.println(arraySplice(list, 2));
//        System.out.println(arraySplice(list, -2));
//        System.out.println(arraySplice(list, 0));
//        System.out.println(arraySplice(list, 1, -5));
//        System.out.println(arraySplice(list, 1, 1));
//        System.out.println(arraySplice(list, 1, list.size(), "orange"));
        System.out.println(arraySplice(list, -1, 1, Arrays.asList("black", "maroon")));
        System.out.println(list);
    }
}
