package com.onevgo.functions;

import java.util.ListIterator;

public class Prev {
    public static <T> T prev(ListIterator<T> array) {
        return array.previous();
    }

    public static void main(String[] args) {
    }
}
