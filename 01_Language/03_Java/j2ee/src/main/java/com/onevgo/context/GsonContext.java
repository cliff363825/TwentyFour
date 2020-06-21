package com.onevgo.context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonContext {
    private static GsonContext instance = new GsonContext();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private GsonContext() {
    }

    public Gson getGson() {
        return gson;
    }

    public static GsonContext getInstance() {
        return instance;
    }
}
