package com.onevgo.functions;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMetaTags {
    private final static Pattern META_PATTERN = Pattern.compile("<meta(?=[^>]*name=(['\"]?)(?<name>.*?)\\1)(?=[^>]*content=(['\"]?)(?<content>.*?)\\3).*?>", Pattern.CASE_INSENSITIVE);

    public static Map<String, String> getMetaTags(String urlStr) {
        Map<String, String> metaTags = new LinkedHashMap<>();
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            Matcher matcher = META_PATTERN.matcher(sb);
            while (matcher.find()) {
                metaTags.put(matcher.group("name"), matcher.group("content"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return metaTags;
    }

    public static void main(String[] args) {
        Map<String, String> metaTags = getMetaTags("https://www.onevgo.com");
        System.out.println(metaTags);
    }
}
