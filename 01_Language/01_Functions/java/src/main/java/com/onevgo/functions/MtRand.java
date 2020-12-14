package com.onevgo.functions;

import java.util.concurrent.ThreadLocalRandom;

public class MtRand {
    public static int mtRand() {
        return mtRand(0, Integer.MAX_VALUE);
    }

    public static int mtRand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static void main(String[] args) {
        System.out.println(mtRand());
        System.out.println(mtRand(5, 15));
        System.out.println(mtRand(1, 3));
    }
}
