package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;

public class Gettype {
    public static String gettype(Object o) {
        if (o == null) {
            return "null";
        }
        return o.getClass().getName();
    }

    public static void main(String[] args) {
        Object[] arr = new Object[]{1, 1.1, null, new Object(), "foo"};
        for (Object o : arr) {
            System.out.println(gettype(o));
        }
    }
}
