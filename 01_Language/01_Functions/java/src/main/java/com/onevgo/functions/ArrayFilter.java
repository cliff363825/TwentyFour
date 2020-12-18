package com.onevgo.functions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrayFilter {
    public static <E> List<E> arrayFilter(Collection<E> input, Predicate<E> predicate) {
        return input.stream().filter(predicate).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(6, 7, 8, 9, 10, 11, 12);

        System.out.println(arrayFilter(list, e -> e % 2 == 1));
        System.out.println(arrayFilter(list2, e -> e % 2 == 0));
    }
}
