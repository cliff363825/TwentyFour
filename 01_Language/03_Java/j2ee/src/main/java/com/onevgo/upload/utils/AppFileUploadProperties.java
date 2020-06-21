package com.onevgo.upload.utils;

import java.util.HashMap;
import java.util.Map;

public class AppFileUploadProperties {
    private Map<String, String> configs = new HashMap<>();
    private static AppFileUploadProperties instance = new AppFileUploadProperties();

    private AppFileUploadProperties() {
    }

    public void setConfig(String name, String value) {
        configs.put(name, value);
    }

    public String getConfig(String name) {
        return configs.get(name);
    }

    public static AppFileUploadProperties getInstance() {
        return instance;
    }
}
