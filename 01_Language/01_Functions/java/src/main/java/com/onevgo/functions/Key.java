package com.onevgo.functions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Key {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("fruit1", "apple");
        map.put("fruit2", "orange");
        map.put("fruit3", "grape");
        map.put("fruit4", "apple");
        map.put("fruit5", "apple");

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            if ("apple".equals(item.getValue())) {
                System.out.println(item.getKey());
            }
        }
    }
}
