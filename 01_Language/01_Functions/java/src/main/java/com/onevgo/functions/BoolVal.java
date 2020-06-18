package com.onevgo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class BoolVal {
    public static boolean boolVal(String s) {
        return s != null && s.length() != 0 && !"0".equals(s);
    }

    public static boolean boolVal(Integer i) {
        return i != null && i != 0;
    }

    public static boolean boolVal(Double d) {
        return d != null && d != 0.0;
    }

    public static boolean boolVal(Object o) {
        return o != null;
    }

    public static boolean boolVal(Collection c) {
        return c != null && c.size() != 0;
    }

    public static boolean boolVal(Map m) {
        return m != null && m.size() != 0;
    }

    public static void main(String[] args) {
        System.out.println("0:        " + (boolVal(0) ? "true" : "false"));
        System.out.println("42:       " + (boolVal(42) ? "true" : "false"));
        System.out.println("0.0:      " + (boolVal(0.0) ? "true" : "false"));
        System.out.println("4.2:      " + (boolVal(4.2) ? "true" : "false"));
        System.out.println("\"\":       " + (boolVal("") ? "true" : "false"));
        System.out.println("\" \":      " + (boolVal(" ") ? "true" : "false"));
        System.out.println("\"string\": " + (boolVal("string") ? "true" : "false"));
        System.out.println("\"true\":   " + (boolVal("true") ? "true" : "false"));
        System.out.println("\"false\":  " + (boolVal("false") ? "true" : "false"));
        System.out.println("\"0\":      " + (boolVal("0") ? "true" : "false"));
        System.out.println("\"1\":      " + (boolVal("1") ? "true" : "false"));
        System.out.println("[1,2]:    " + (boolVal(Arrays.asList(1, 2)) ? "true" : "false"));
        System.out.println("[]:       " + (boolVal(new ArrayList()) ? "true" : "false"));
        System.out.println("object:   " + (boolVal(new Object()) ? "true" : "false"));
    }
}
