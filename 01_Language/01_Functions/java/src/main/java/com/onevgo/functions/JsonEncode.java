package com.onevgo.functions;

import java.util.HashMap;
import java.util.Map;

public class JsonEncode {
    public static String jsonEncode(Object value) {
        return JsonDecode.GSON.toJson(value);
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        System.out.println(jsonEncode(map));
    }
}
