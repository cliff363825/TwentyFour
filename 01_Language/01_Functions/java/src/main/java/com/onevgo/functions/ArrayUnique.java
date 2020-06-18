package com.onevgo.functions;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ArrayUnique {
    public static <E> List<E> arrayUnique(List<E> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public static void main(String[] args) {

    }
}
