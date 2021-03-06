package com.onevgo.functions;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Implode {
    public static String implode(String glue, Iterable<?> pieces) {
        return StringUtils.join(pieces, glue);
    }

    public static void main(String[] args) {
        List<String> array = Arrays.asList("lastname", "email", "phone");
        System.out.println(implode(",", array));
        System.out.println(implode(",", Collections.emptyList()));
    }
}
