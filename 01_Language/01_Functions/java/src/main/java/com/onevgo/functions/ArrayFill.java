package com.onevgo.functions;

import java.util.Arrays;

public class ArrayFill {
    public static void main(String[] args) {
        String[] strings = new String[11];
        Arrays.fill(strings, 5, 11, "banana");
        System.out.println(Arrays.toString(strings));
    }
}
