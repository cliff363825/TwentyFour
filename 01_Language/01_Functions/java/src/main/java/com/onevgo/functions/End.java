package com.onevgo.functions;

import java.util.ListIterator;

public class End {
    public static <E> E end(ListIterator<E> list) {
        E result = null;
        while (list.hasNext()) {
            result = list.next();
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
