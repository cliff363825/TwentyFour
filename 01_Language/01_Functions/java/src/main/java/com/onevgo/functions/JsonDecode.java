package com.onevgo.functions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonDecode {
    static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    static final TypeToken<Map<String, Object>> MAP_TYPE_TOKEN = new TypeToken<Map<String, Object>>(){};
    static final TypeToken<List<Object>> LIST_TYPE_TOKEN = new TypeToken<List<Object>>(){};

    public static <T> T jsonDecode(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static <T> T jsonDecode(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static Map<String, Object> jsonDecodeToMap(String json) {
        return jsonDecode(json, MAP_TYPE_TOKEN.getType());
    }

    public static List<Object> jsonDecodeToList(String json) {
        return jsonDecode(json, LIST_TYPE_TOKEN.getType());
    }

    public static void main(String[] args) {
        String json = "{\"a\":1,\"b\":2,\"c\":3,\"d\":4,\"e\":5}";
        Map map = jsonDecode(json, Map.class);
        System.out.println(map);

        System.out.println(jsonDecodeToMap(json));
    }
}
