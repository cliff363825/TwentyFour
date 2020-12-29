package com.onevgo.functions;

public class Printf {
    public static void main(String[] args) {
        int n = 43951789;
        int u = -43951789;
        int c = 65;

        System.out.printf("%%b = '%b'\n", n);
        System.out.printf("%%c = '%c'\n", c);
        System.out.printf("%%d = '%d'\n", n);
//        System.out.printf("%%e = '%e'\n", n);
//        System.out.printf("%%u = '%u'\n", n);
//        System.out.printf("%%u = '%u'\n", u);
//        System.out.printf("%%f = '%f'\n", n);
        System.out.printf("%%o = '%o'\n", n);
        System.out.printf("%%s = '%s'\n", n);
        System.out.printf("%%x = '%x'\n", n);
        System.out.printf("%%X = '%X'\n", n);

        System.out.printf("%%+d = '%+d'\n", n);
        System.out.printf("%%+d = '%+d'\n", u);
    }
}
