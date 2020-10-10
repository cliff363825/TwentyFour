package com.onevgo.functions;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpBuildQuery {
    public static String httpBuildQuery(Object queryData) {
        return httpBuildQuery(queryData, "");
    }

    public static String httpBuildQuery(Object queryData, String numericPrefix) {
        return httpBuildQuery(queryData, numericPrefix, '&');
    }

    public static String httpBuildQuery(Object queryData, String numericPrefix, char argSeparator) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        formatKey(queryData, nameValuePairs, numericPrefix, "");
        return URLEncodedUtils.format(nameValuePairs, argSeparator, StandardCharsets.UTF_8);
    }

    private static void formatKey(Object queryData, List<NameValuePair> nameValuePairs, String numericPrefix, String prefix) {
        numericPrefix = numericPrefix == null ? "" : numericPrefix;
        prefix = prefix == null ? "" : prefix;

        if (queryData == null) {
            if (prefix.length() != 0) {
                nameValuePairs.add(new BasicNameValuePair(prefix, null));
            }
        } else if (Iterable.class.isAssignableFrom(queryData.getClass())) {
            Iterable iterable = (Iterable) queryData;
            Iterator iterator = iterable.iterator();
            for (int i = 0; iterator.hasNext(); i++) {
                Object o = iterator.next();
                if (prefix.length() == 0) {
                    formatKey(o, nameValuePairs, "", numericPrefix + i);
                } else {
                    formatKey(o, nameValuePairs, "", prefix + "[" + i + "]");
                }
            }
        } else if (Map.class.isAssignableFrom(queryData.getClass())) {
            Map map = (Map) queryData;
            for (Object o : map.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (key == null) {
                    continue;
                }
                if (prefix.length() == 0) {
                    if (numericPrefix.length() != 0 && Number.class.isAssignableFrom(key.getClass())) {
                        formatKey(value, nameValuePairs, "", numericPrefix + key.toString());
                    } else {
                        formatKey(value, nameValuePairs, "", key.toString());
                    }
                } else {
                    formatKey(value, nameValuePairs, "", prefix + "[" + key.toString() + "]");
                }
            }
        } else {
            if (prefix.length() != 0) {
                nameValuePairs.add(new BasicNameValuePair(prefix, queryData.toString()));
            }
        }
    }

    public static void main(String[] args) {
        Map<Object, Object> map = new LinkedHashMap<>();
        map.put("foo", "bar");
        map.put("baz", "boom");
        map.put("cow", "milk");
        map.put("php", "hypertext processor");
        map.put(0, Arrays.asList("a", "b", "c"));
        System.out.println(httpBuildQuery(map));
    }
}
