package com.onevgo.functions;

import cn.hutool.core.util.ReUtil;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PregSplit {
    public static List<String> pregSplit(String pattern, String subject) {
        Pattern compile = Pattern.compile(pattern);
        return compile.splitAsStream(subject).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(pregSplit("[\\s,]+", "hypertext language, programming"));

    }
}
