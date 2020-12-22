package com.onevgo.functions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PregGrep {
    public static List<String> pregGrep(String pattern, Collection<String> array) {
        Pattern compile = Pattern.compile(pattern);
        return array.stream().filter(s -> compile.matcher(s).find()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> array = Arrays.asList("1.23", ".45", "abc");
        System.out.println(pregGrep("^(\\d+)?\\.\\d+$", array));
        System.out.println(pregGrep("\\d+", array));
    }
}
