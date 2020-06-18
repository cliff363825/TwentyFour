package com.onevgo.functions;

import java.util.List;

public class End {
    public static <E> E end(List<E> list) {
        if (list == null || list.size() == 0) return null;
        return list.get(list.size() - 1);
    }

    public static <E> E end(E[] arr) {
        if (arr == null || arr.length == 0) return null;
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {

    }
}
