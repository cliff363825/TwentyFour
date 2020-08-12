package com.onevgo.functions;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetHeaders {
    public static Map<String, List<String>> getHeaders(String url) {
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
            return urlConnection.getHeaderFields();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getHeaders("http://www.onevgo.com"));
    }
}
