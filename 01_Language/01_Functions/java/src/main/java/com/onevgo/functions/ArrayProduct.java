package com.onevgo.functions;

import java.util.Collection;

public class ArrayProduct {
    public static long arrayProduct(Collection<Long> col) {
        return col.stream().reduce(1L, (l1, l2) -> l1 * l2);
    }

    public static long arrayProduct(long[] longArr) {
        long res = 1L;
        for (long l : longArr) {
            res *= l;
        }
        return res;
    }

    public static void main(String[] args) {
        long[] l = new long[]{2, 4, 6, 8};
        System.out.println(arrayProduct(l));
        System.out.println(arrayProduct(new long[0]));
    }
}
