package com.onevgo.functions;

import java.util.*;
import java.util.stream.IntStream;

public class ArrayRand {
    private static final Random random = new Random();

    public static <E> List<Integer> arrayRand(List<E> list, int num) {
        List<Integer> res = new ArrayList<>(num);
        // ranges = [ 0, list.size() )
        List<Integer> ranges = IntStream.range(0, list.size()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        int endIndex = ranges.size() - 1;
        for (int i = 0; i < num; i++) {
            int targetIndex = random.nextInt(endIndex + 1);
            res.add(ranges.get(targetIndex));
            ranges.set(targetIndex, ranges.get(endIndex));
            endIndex--;
        }
        return res;
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
