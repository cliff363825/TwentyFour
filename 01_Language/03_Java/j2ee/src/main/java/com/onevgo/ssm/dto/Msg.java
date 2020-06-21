package com.onevgo.ssm.dto;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private int code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    public static Msg success() {
        Msg msg = new Msg();
        msg.setCode(0);
        msg.setMsg("SUCCESS");
        return msg;
    }

    public static Msg fail() {
        Msg msg = new Msg();
        msg.setCode(1000);
        msg.setMsg("FAIL");
        return msg;
    }

    public Msg add(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
