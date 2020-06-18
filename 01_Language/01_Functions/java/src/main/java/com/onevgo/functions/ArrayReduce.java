package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ArrayReduce {
    public static <E> Optional<E> arrayReduce(List<E> list, BinaryOperator<E> accumulator) {
        return list.stream().reduce(accumulator);
    }

    public static <E> E arrayReduce(List<E> list, BinaryOperator<E> accumulator, E initialValue) {
        return list.stream().reduce(initialValue, accumulator);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(arrayReduce(list, Integer::sum).orElse(0));
        System.out.println(arrayReduce(list, (x, y) -> x * y, 10));
        System.out.println(arrayReduce(new ArrayList<>(), Integer::sum, 10));
    }
}
