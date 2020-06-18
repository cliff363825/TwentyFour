package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPad {
    public static <E> List<E> arrayPad(List<E> list, int size, E value) {
        int srcSize = list.size();
        int destSize = Math.abs(size);
        if (srcSize >= destSize) {
            // not padded
            return new ArrayList<>(list);
        }

        List<E> res = new ArrayList<>(destSize);
        for (int i = 0, n = destSize - srcSize; i < n; i++) {
            if (size > 0 && i == 0) {
                res.addAll(list);
            }
            res.add(value);
            if (size < 0 && i == n - 1) {
                res.addAll(list);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(12, 10, 9);
        System.out.println(arrayPad(input, 5, 0));
        System.out.println(arrayPad(input, -7, -1));
        System.out.println(arrayPad(input, 2, 999));
    }
}
