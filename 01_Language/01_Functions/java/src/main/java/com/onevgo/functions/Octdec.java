package com.onevgo.functions;

public class Octdec {
    public static long octdec(String octalString) {
        return Long.parseLong(octalString, 8);
    }

    public static void main(String[] args) {
        System.out.println(octdec("77"));
    }
}
