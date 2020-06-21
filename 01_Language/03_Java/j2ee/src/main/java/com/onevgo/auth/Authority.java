package com.onevgo.auth;

import lombok.Data;

@Data
public class Authority {
    private String displayName;
    private String url;

    public Authority(String displayName, String url) {
        this.displayName = displayName;
        this.url = url;
    }
}
