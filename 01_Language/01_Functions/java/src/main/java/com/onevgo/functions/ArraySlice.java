package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySlice {
    public static <E> List<E> arraySlice(List<E> list, int offset) {
        int startIndex = calcStartIndex(list, offset);
        return new ArrayList<>(list.subList(startIndex, list.size()));
    }

    public static <E> List<E> arraySlice(List<E> list, int offset, int length) {
        int startIndex = calcStartIndex(list, offset);
        int endIndex = calcEndIndex(list, startIndex, length);
        return new ArrayList<>(list.subList(startIndex, endIndex));
    }

    private static <E> int calcStartIndex(List<E> list, int offset) {
        if (offset >= 0) {
            return Math.min(offset, list.size()); // [0, size)
        } else {
            return Math.max(list.size() + offset, 0); // [0, size)
        }
    }

    private static <E> int calcEndIndex(List<E> list, int startIndex, int length) {
        if (length >= 0) {
            return Math.min(startIndex + length, list.size()); // [start+len, size)
        } else {
            return Math.max(list.size() + length, startIndex); // [start, size-len)
        }
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println(arraySlice(input, 2));
        System.out.println(arraySlice(input, -2, 1));
        System.out.println(arraySlice(input, 0, 3));
        System.out.println(arraySlice(input, 1, -5));
    }
}
