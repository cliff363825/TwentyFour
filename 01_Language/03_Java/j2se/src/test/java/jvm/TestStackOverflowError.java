package jvm;

public class TestStackOverflowError {
    public static void main(String[] args) {
//        testStackOverflowError();
    }

    public static void testStackOverflowError() {
        testStackOverflowError(); // Exception in thread "main" java.lang.StackOverflowError
    }
}
