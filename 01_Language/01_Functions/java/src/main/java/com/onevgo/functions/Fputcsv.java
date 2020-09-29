package com.onevgo.functions;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Fputcsv {
    public static void main(String[] args) {
        String[][] strings = {
                {"aaa", "bbb", "ccc", "dd,dd"},
                {"123", "456", "789"},
                {"\"aaa\"", "\"bbb\""},
        };
        CsvWriter writer = CsvUtil.getWriter(new File("test.csv"), StandardCharsets.UTF_8);
        for (String[] string : strings) {
            writer.write(string);
        }
        writer.close();
    }
}
