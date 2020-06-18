package com.onevgo.functions;

public class Assert {
    public static void main(String[] args) {
        // VM options: -ea
        assert 1 > 2 : "Oops! Correct: 1 < 2";
    }
}
