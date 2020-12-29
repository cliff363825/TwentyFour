package com.onevgo.functions;

import java.util.stream.IntStream;

public class Range {
    public static IntStream range(int start, int end) {
        if (start > end) {
            return IntStream.rangeClosed(end, start).map(i -> start - i + end);
        }
        return IntStream.rangeClosed(start, end);
    }

    public static void main(String[] args) {
        range(0, 12).forEach(System.out::println);
        range('a', 'i').forEach(c -> {
            System.out.println((char) c);
        });
        range('c', 'a').forEach(c -> {
            System.out.println((char) c);
        });
    }
}
