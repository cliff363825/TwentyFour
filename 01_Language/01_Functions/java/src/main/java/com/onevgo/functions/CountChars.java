package com.onevgo.functions;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CountChars {
    public static Map<Character, Integer> countChars(String s) {
        return countChars(s, 0);
    }

    public static Map<Character, Integer> countChars(String s, int mode) {
        if (mode == 3 || mode == 4) throw new UnsupportedOperationException();
        LinkedHashMap<Character, Integer> res = new LinkedHashMap<>();
        int[] counts = new int[256];
        // byte: -128-127
        // ascii: 0-255
        byte[] bytes = s.getBytes(Charset.defaultCharset());
        for (byte b : bytes) {
            counts[b & 0xff]++;
        }

        for (int i = 0; i < counts.length; i++) {
            if ((mode == 1 && counts[i] == 0) ||
                    (mode == 2 && counts[i] != 0)) {
                continue;
            }
            res.put((char) i, counts[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String data = "Two Ts and one F.";
        for (Map.Entry<Character, Integer> entry : countChars(data, 1).entrySet()) {
            System.out.println("[" + (int) entry.getKey() + "]: There were " + entry.getValue() + " instance(s) of \"" + entry.getKey() + "\" in the string.");
        }
    }
}
