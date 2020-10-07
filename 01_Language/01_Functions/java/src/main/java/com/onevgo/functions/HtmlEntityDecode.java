package com.onevgo.functions;

import org.apache.commons.text.StringEscapeUtils;

public class HtmlEntityDecode {
    public static String htmlEntityDecode(String string) {
        return StringEscapeUtils.unescapeHtml4(string);
    }

    public static void main(String[] args) {
        String a = "I'll &quot;walk&quot; the &lt;b&gt;dog&lt;/b&gt; now";
        System.out.println(htmlEntityDecode(a));
    }
}
