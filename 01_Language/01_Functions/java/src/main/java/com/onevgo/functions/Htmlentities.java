package com.onevgo.functions;

import org.apache.commons.text.StringEscapeUtils;

public class Htmlentities {
    public static String htmlentities(String string) {
        return StringEscapeUtils.escapeHtml4(string);
    }

    public static void main(String[] args) {
        System.out.println(htmlentities("A 'quote' is <b>bold</b>"));
        System.out.println(htmlentities("<Il était une fois un être>."));
    }
}
