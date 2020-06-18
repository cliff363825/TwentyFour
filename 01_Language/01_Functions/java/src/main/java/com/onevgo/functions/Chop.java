package com.onevgo.functions;

public class Chop {
    // https://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/StringUtils.html#line.854
    public static String chop(final String str) {
        return chop(str, null);
    }

    public static String chop(final String str, final String stripChars) {
        if (str == null) return "";
        if (str.length() == 0) return str;

        int end = str.length();
        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    public static void main(String[] args) {
        String text = "\t\tThese are a few words :) ...  ";
//        String binary = "\x09Example string\x0A";
        String hello = "Hello World";
        System.out.println(text);
//        System.out.println(binary);
        System.out.println(hello);

        System.out.println(chop(text));
        System.out.println(chop(text, " \t."));
        System.out.println(chop(hello, "Hdle"));

        // trim the ASCII control characters at the end of $binary
        // (from 0 to 31 inclusive)
//        System.out.println(chop(binary, "\x00..\x1F"));
    }
}
