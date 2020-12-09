package com.onevgo.functions;

public class MemoryGetUsage {
    public static long memoryGetUsage() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static void main(String[] args) {

    }
}
