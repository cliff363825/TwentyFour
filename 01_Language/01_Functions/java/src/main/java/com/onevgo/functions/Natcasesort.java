package com.onevgo.functions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Natcasesort {
    public static boolean natcasesort(List<String> array) {
        array.sort((o1, o2) -> {
            if (o1 != null && o2 != null) {
                return o1.compareToIgnoreCase(o2);
            } else if (o1 != null) {
                return 1;
            } else if (o2 != null) {
                return -1;
            } else {
                return 0;
            }
        });
        return true;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("IMG0.png", "img12.png", "img10.png", "img2.png", null, "img1.png", "IMG3.png");
        natcasesort(list);
        System.out.println(list);
    }
}
