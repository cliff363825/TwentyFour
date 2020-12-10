package com.onevgo.functions;

import java.util.*;
import java.util.stream.IntStream;

public class ArrayRand {
    private static final Random random = new Random();

    public static <E> List<Integer> arrayRand(List<E> list, int num) {
        if (num < 1 || num > list.size()) {
            throw new IllegalArgumentException("num");
        }

        List<Integer> result = new ArrayList<>(num);
        // ranges = [ 0, list.size() )
        int[] ranges = IntStream.range(0, list.size()).toArray();
        int len = ranges.length;
        for (int i = 0; i < num; i++) {
            int p = random.nextInt(len - i);
            result.add(ranges[p]);
            ranges[p] = ranges[len - 1 - i];
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Neo", "Morpheus", "Trinity", "Cypher", "Tank");
        System.out.println(arrayRand(list, 2));
        System.out.println(arrayRand(list, 2));
        System.out.println(arrayRand(list, 2));
        System.out.println(arrayRand(list, 2));
        System.out.println(arrayRand(list, 2));
    }
}
