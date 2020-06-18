package com.onevgo.functions;

import cn.hutool.core.codec.Base64;

public class ChunkSplit {
    public static String chunkSplit(String body) {
        return chunkSplit(body, 76, "\r\n");
    }

    public static String chunkSplit(String body, int chunkLen, String end) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start + chunkLen <= body.length()) {
            sb.append(body, start, start + chunkLen).append(end);
            start += chunkLen;
        }
        if (start < body.length()) {
            sb.append(body, start, body.length()).append(end);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // format $data using RFC 2045 semantics
        String data = "Returns the chunked string.";
        String new_string = chunkSplit(Base64.encode(data) + Base64.encode(data) + Base64.encode(data) + Base64.encode(data) + Base64.encode(data));
        System.out.println(new_string);
    }
}
