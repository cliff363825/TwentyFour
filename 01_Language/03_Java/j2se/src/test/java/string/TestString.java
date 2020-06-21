package string;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

public class TestString {
    @Test
    public void test1() {
        String str1 = "JavaEE";
        String str2 = "JavaEE";
        String str3 = new String("JavaEE");

        String str4 = "JavaEE" + "Android";
        String str5 = "Android";
        String str6 = str1 + str5;
        // str6.intern()伪代码实现：
//        if (!String.class.stringsPool.containsKey(str6)) {
//            String.class.stringsPool.put(str6, str6);
//        }
//        return String.class.stringsPool.get(str6);
        String str7 = str6.intern();
        String str8 = "JavaEEAndroid";

        System.out.println(str1 == str2); // true
        System.out.println(str1 == str3); // false
        System.out.println(str1.equals(str3)); // true

        System.out.println(str4 == str6); // false
        System.out.println(str4.equals(str6)); // true
        System.out.println(str4 == str7); // true
        System.out.println(str4 == str8); // true
    }

    @Test
    public void test2() {
        String str1 = "abcdefghijkabcc";
        String str2 = "abcc";
        String str3 = "中文";
        String str4 = "中国";

        System.out.println(str1.length()); // 15
        System.out.println(str3.length()); // 2
        System.out.println(str3.charAt(1)); // 文
        System.out.println(str1.equals(str2)); // false
        System.out.println(str1.compareTo(str2)); // 1
        System.out.println(str3.compareTo(str4)); // 3722
        System.out.println(str1.indexOf(str2)); // 11
        System.out.println(str1.lastIndexOf(str2)); // 11
        System.out.println(str1.startsWith(str2)); // false
        System.out.println(str1.endsWith(str2)); // true
        System.out.println(str1.regionMatches(11, str2, 0, str2.length())); // true
    }

    @Test
    public void test3() {
        String str1 = "Hello world!";
        String str2 = " a b c  ";
        String str3 = "001-002-100-101-102";

        System.out.println(str1.substring(6)); // world!
        System.out.println(str1.substring(6, 11)); // world
        System.out.println(str1.replace("Hello", "Hi")); // php str_replace()
        System.out.println(str1.replaceAll("\\s", "\t")); // php preg_replace()
        System.out.println(str2); //  a b c
        System.out.println(str2.trim()); // a b c
        System.out.println(str2.concat(" d e f")); //  a b c   d e f
        System.out.println(Arrays.toString(str3.split("-"))); // [001, 002, 100, 101, 102]
    }

    @Test
    public void testStringToPrimitive() {
        // String -> int/Integer...
        String str = "996";

        int i = Integer.parseInt(str);
        long l = Long.parseLong(str);
        float f = Float.parseFloat(str);
        double d = Double.parseDouble(str);


        System.out.println("i = " + i);
        System.out.println("l = " + l);
        System.out.println("f = " + f);
        System.out.println("d = " + d);

        String str4 = "中文";
        byte[] b = str4.getBytes(Charset.forName("UTF-8"));
        System.out.println(Arrays.toString(b)); // [-28, -72, -83, -26, -106, -121]

        String str5 = new String(b, Charset.forName("UTF-8"));
        System.out.println(str5); // 中文

        char[] c = str4.toCharArray();
        System.out.println(Arrays.toString(c)); // [中, 文]
        System.out.println(new String(c)); // 中文
    }

    @Test
    public void testPrimitiveToString() {
        // int/Integer... -> String
        int i = 996;
        long l = 996L;
        float f = 996F;
        double d = 996D;

        String iStr = String.valueOf(i);
        String lStr = String.valueOf(l);
        String fStr = String.valueOf(f);
        String dStr = String.valueOf(d);

        System.out.println("iStr = " + iStr);
        System.out.println("lStr = " + lStr);
        System.out.println("fStr = " + fStr);
        System.out.println("dStr = " + dStr);
    }

    @Test
    public void testCharArrayToString() {
        char[] chars = new char[]{'H', 'e', 'l', 'l', 'o', ',', '中', '国'};
        String s = new String(chars);
        System.out.println("s = " + s);

        String s1 = new String(chars, 0, 5);
        System.out.println("s1 = " + s1);
    }

    @Test
    public void testStringToCharArray() {
        String s = "hello,中国";
        char[] chars = s.toCharArray();
        System.out.println("chars = " + Arrays.toString(chars));

        char[] chars1 = new char[5];
        s.getChars(0, 5, chars1, 0);
        System.out.println("chars1 = " + Arrays.toString(chars1));
    }

    @Test
    public void testByteArrayToString() {
        byte[] bytes = new byte[]{65, 66, 67, 97, 98, 99};
        String s = new String(bytes);
        System.out.println("s = " + s);
    }

    @Test
    public void testStringToByteArray() {
        String s = "hello,中国";
        byte[] bytes = s.getBytes();
        // bytes = [104, 101, 108, 108, 111, 44, -28, -72, -83, -27, -101, -67]
        System.out.println("bytes = " + Arrays.toString(bytes));

        byte[] bytes1 = s.getBytes(Charset.forName("UTF-8"));
        // bytes1 = [104, 101, 108, 108, 111, 44, -28, -72, -83, -27, -101, -67]
        System.out.println("bytes1 = " + Arrays.toString(bytes1));

        byte[] bytes2 = s.getBytes(Charset.forName("GBK"));
        // bytes2 = [104, 101, 108, 108, 111, 44, -42, -48, -71, -6]
        System.out.println("bytes2 = " + Arrays.toString(bytes2));
    }
}
