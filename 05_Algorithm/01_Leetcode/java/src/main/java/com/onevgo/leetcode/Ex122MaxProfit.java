package com.onevgo.leetcode;

public class Ex122MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int prevPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prevPrice) {
                maxProfit += (prices[i] - prevPrice);
            }
            prevPrice = prices[i];
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(new Ex122MaxProfit().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
