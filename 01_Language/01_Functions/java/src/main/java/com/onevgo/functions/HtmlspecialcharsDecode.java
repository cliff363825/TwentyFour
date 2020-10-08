package com.onevgo.functions;

import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.LookupTranslator;

import static org.apache.commons.text.translate.EntityArrays.BASIC_UNESCAPE;

public class HtmlspecialcharsDecode {
    private final static CharSequenceTranslator UNESCAPE_HTML_SPECIAL = new LookupTranslator(BASIC_UNESCAPE);

    public static String htmlspecialcharsDecode(String string) {
        return UNESCAPE_HTML_SPECIAL.translate(string);
    }

    public static void main(String[] args) {
        System.out.println(htmlspecialcharsDecode("<p>this -&gt; &quot;</p>\n"));
    }
}
