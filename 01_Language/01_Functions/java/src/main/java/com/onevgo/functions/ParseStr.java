package com.onevgo.functions;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ParseStr {
    public static List<NameValuePair> parseStr(String str) {
        return URLEncodedUtils.parse(str, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String s = "first=value&arr[]=foo+bar&arr[]=baz";
        System.out.println(parseStr(s));
    }
}
