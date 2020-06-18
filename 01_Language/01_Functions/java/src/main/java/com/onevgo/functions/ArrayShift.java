package com.onevgo.functions;

import java.util.List;

public class ArrayShift {
    public static <E> E arrayShift(List<E> list) {
        return list.remove(0);
    }

    public static void main(String[] args) {

    }
}
