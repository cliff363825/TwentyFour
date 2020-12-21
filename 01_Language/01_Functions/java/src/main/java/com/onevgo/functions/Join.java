package com.onevgo.functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Join {
    public static String join(String glue, List<String> pieces) {
        return String.join(glue, pieces);
    }

    public static void main(String[] args) {
        List<String> array = Arrays.asList("lastname", "email", "phone");
        System.out.println(join(",", array));

        System.out.println(join("hello", Collections.emptyList()));
    }
}
