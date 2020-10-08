package com.onevgo.functions;

import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.LookupTranslator;

import static org.apache.commons.text.translate.EntityArrays.BASIC_ESCAPE;

public class Htmlspecialchars {
    private final static CharSequenceTranslator ESCAPE_HTML_SPECIAL = new LookupTranslator(BASIC_ESCAPE);

    public static String htmlspecialchars(String string) {
        return ESCAPE_HTML_SPECIAL.translate(string);
    }

    public static void main(String[] args) {
        System.out.println(htmlspecialchars("<a href='test'>Test</a>"));
    }
}
