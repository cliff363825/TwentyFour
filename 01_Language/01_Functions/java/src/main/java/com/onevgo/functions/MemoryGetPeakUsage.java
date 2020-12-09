package com.onevgo.functions;

public class MemoryGetPeakUsage {
    public static long memoryGetPeakUsage() {
        return Runtime.getRuntime().maxMemory();
    }

    public static void main(String[] args) {
        System.out.println(memoryGetPeakUsage() / 1024 / 1024 + " MB");
    }
}
