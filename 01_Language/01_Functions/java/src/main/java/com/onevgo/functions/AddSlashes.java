package com.onevgo.functions;

public class AddSlashes {
    public static String addSlashes(String s) {
        return s.replace("\\", "\\\\")
                .replace("'", "\\\'")
                .replace("\"", "\\\"")
                .replace("\0", "\\0");
    }

    public static void main(String[] args) {
        String str = "' \" \\ \0";
        System.out.println(str);
        System.out.println(addSlashes(str));
    }
}
