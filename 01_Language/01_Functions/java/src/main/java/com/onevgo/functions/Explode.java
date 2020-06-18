package com.onevgo.functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Explode {
    public static List<String> explode(String delimiter, String str) {
        return explode(delimiter, str, 0);
    }

    public static List<String> explode(String delimiter, String str, int limit) {
        if (str == null) return Collections.emptyList();
        return Arrays.asList(str.split(delimiter, limit));
    }

    public static void main(String[] args) {
        String pizza = "piece1 piece2 piece3 piece4 piece5 piece6";
        System.out.println(explode(" ", pizza));

        String data = "foo:*:1023:1000::/home/foo:/bin/sh";
        System.out.println(explode(":", data));

        String str = "one|two|three|four";
        System.out.println(explode("\\|", str, 2));
    }
}
