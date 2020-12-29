package com.onevgo.functions;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class Rawurldecode {
    public static String rawurldecode(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream(bytes.length);
        int b;
        for (int i = 0; i < bytes.length; i++) {
            b = bytes[i];
            if (b == '%') {
                if (i + 1 < bytes.length) {
                    final int u = Character.digit(bytes[i + 1], 16);
                    if (u >= 0 && i + 2 < bytes.length) {
                        final int l = Character.digit(bytes[i + 2], 16);
                        if (l >= 0) {
                            buffer.write((char) ((u << 4) + l));
                            i += 2;
                            continue;
                        }
                    }
                }
                // 跳过不符合规范的%形式
                buffer.write(b);
            } else {
                buffer.write(b);
            }
        }
        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println(rawurldecode("foo%20bar%40baz"));
        System.out.println(rawurldecode("foo+bar%40baz"));
    }
}
