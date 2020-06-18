package com.onevgo.functions;

import cn.hutool.core.util.HexUtil;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class Bin2Hex {
    private final static char[] DIGITS_LOWER = "0123456789abcdef".toCharArray();

    public static String bin2Hex(byte[] data) {
        final int len = data.length;
        final char[] out = new char[len << 1];//len*2
        // two characters from the hex value.
        for (int i = 0, j = 0; i < len; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];// 高位
            out[j++] = DIGITS_LOWER[0x0F & data[i]];// 低位
        }
        return new String(out);
    }

    public static void main(String[] args) {
        System.out.println(bin2Hex("中国".getBytes()));
        // hutool
        System.out.println(HexUtil.encodeHexStr("中国"));
        System.out.println(new String(HexUtil.decodeHex("e4b8ade59bbd")));

        String s = "1110100001101001";
        // e869
        byte[] bytes = new byte[(int) Math.ceil(s.length() / 4.0)];
        int j = bytes.length - 1;
        for (int i = s.length(); i > 0; i -= 4) {
            String s1 = null;
            if (i - 4 >= 0) {
                s1 = s.substring(i - 4, i);
            } else {
                s1 = s.substring(0, i);
            }
            bytes[j--] = Byte.valueOf(s1, 2);
        }
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));
    }
}
