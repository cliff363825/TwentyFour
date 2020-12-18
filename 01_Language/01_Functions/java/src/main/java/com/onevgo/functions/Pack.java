package com.onevgo.functions;

import java.util.ArrayList;
import java.util.List;

public class Pack {
    public static void main(String[] args) {
        List<Byte> bytes = new ArrayList<>();
        bytes.add((byte) 0x12);
        bytes.add((byte) 0x34);
        bytes.add((byte) 0x78);
        bytes.add((byte) 0x56);
        bytes.add((byte) 65);
        bytes.add((byte) 66);
        System.out.println(bytes);
    }
}
