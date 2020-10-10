package com.onevgo.functions;

import java.io.UnsupportedEncodingException;

public class Iconv {
    public static String iconv(String inCharset, String outCharset, String str) {
        try {
            return new String(str.getBytes(inCharset), outCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        String text = "This is the Euro symbol '€'.";
        System.out.println(text);
        System.out.println(iconv("utf-8", "iso-8859-1", text));
    }
}
