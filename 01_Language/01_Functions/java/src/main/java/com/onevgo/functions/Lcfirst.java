package com.onevgo.functions;

import org.apache.commons.lang3.StringUtils;

public class Lcfirst {
    public static String lcfirst(String str) {
        return str == null ? "" : StringUtils.uncapitalize(str);
    }

    public static void main(String[] args) {
        String foo = "HelloWorld";
        System.out.println(lcfirst(foo));

        String bar = "HELLO WORLD!";
        System.out.println(lcfirst(bar));

        System.out.println(lcfirst(" Abc"));
        System.out.println(lcfirst(null));
    }
}
