package com.onevgo.leetcode;

public class Ex38CountAndSay {
    public String countAndSay(int n) {
        //1.     1
        //2.     11
        //3.     21
        //4.     1211
        //5.     111221
        String preStr = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            Character prev = null;
            for (char c : preStr.toCharArray()) {
                if (prev == null) {
                    // init
                    prev = c;
                    count = 1;
                    continue;
                }
                if (c == prev) {
                    count++;
                } else {
                    sb.append(count).append(prev);
                    // reset
                    prev = c;
                    count = 1;
                }
            }
            if (count > 0) {
                // over
                sb.append(count).append(prev);
            }
            preStr = sb.toString();
        }
        return preStr;
    }

    public static void main(String[] args) {
        System.out.println(new Ex38CountAndSay().countAndSay(4));
    }
}
