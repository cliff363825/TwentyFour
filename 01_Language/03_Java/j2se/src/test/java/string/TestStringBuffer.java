package string;

import org.junit.Test;

public class TestStringBuffer {
    @Test
    public void test1() {
        StringBuffer sb = new StringBuffer();
        sb.append("Hello").append(" ").append("world!");
        sb.insert(5, ',');
        System.out.println(sb.toString()); // Hello, world!
        sb.replace(7, sb.length(), "Song!");
        System.out.println(sb); // Hello, Song!
    }

    @Test
    public void test2() {
        long startTime = 0L;
        long endTime = 0L;

        // 1. StringBuffer
        StringBuffer stringBuffer = new StringBuffer("");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            stringBuffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime)); // StringBuffer的执行时间：42

        // 2. StringBuilder
        StringBuilder stringBuilder = new StringBuilder("");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            stringBuilder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime)); // StringBuilder的执行时间：6

        // 3. String
        String str = "";
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            str = str + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间：" + (endTime - startTime)); // String的执行时间：5649
    }
}
