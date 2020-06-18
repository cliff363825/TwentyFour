package com.onevgo.leetcode;

public class Ex137SingleNumber {
    public int singleNumber(int[] nums) {
        // 不妨设 num = 0
        // 如果一个数 x
        // 出现一次，num = num ^ x = 0 ^ x = x
        // 出现两次，num = num ^ x = x ^ x = 0
        // 出现三次，num = num ^ x = 0 ^ x = x
        // 如何区分出现一次还是三次？
        // seenOnce，表示出现一次，seenTwice，表示出现两次
        // seenOnce = (seenOnce ^ x) & ~seenTwice; 出现一次，且不出现两次 => 仅出现一次
        // seenTwice = (seenTwice ^ x) & ~seenOnce; 出现两次
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearance:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }

    public static void main(String[] args) {

    }
}
