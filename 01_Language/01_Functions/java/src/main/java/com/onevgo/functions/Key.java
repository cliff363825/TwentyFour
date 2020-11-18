package com.onevgo.functions;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;

import java.util.Map;

public class Key {
    public static void main(String[] args) {
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder()
                .put("fruit1", "apple")
                .put("fruit2", "orange")
                .put("fruit3", "grape")
                .put("fruit4", "apple")
                .put("fruit5", "apple")
                .build();

        UnmodifiableIterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            if ("apple".equals(item.getValue())) {
                System.out.println(item.getKey());
            }
        }
    }
}
