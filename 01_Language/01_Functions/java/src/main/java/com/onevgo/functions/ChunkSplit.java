package com.onevgo.functions;

public class ChunkSplit {
    public static String chunkSplit(String body) {
        return chunkSplit(body, 76, "\r\n");
    }

    public static String chunkSplit(String body, int chunklen, String end) {
        StringBuilder sb = new StringBuilder(body);
        for (int i = chunklen, j = 0; i < body.length(); i += chunklen, j++) {
            sb.insert(i + j * end.length(), end);
        }
        sb.append(end);
        return sb.toString();
    }

    private static String base64Encode(String data) {
        return Base64Encode.base64Encode(data.getBytes());
    }

    public static void main(String[] args) {
        // format $data using RFC 2045 semantics
        String data = "Returns the chunked string.";
        String new_string = chunkSplit((base64Encode(data) + base64Encode(data) + base64Encode(data) + base64Encode(data) + base64Encode(data)).substring(0, 75));
        System.out.println(new_string);
        System.out.println(new_string.length());
    }
}
