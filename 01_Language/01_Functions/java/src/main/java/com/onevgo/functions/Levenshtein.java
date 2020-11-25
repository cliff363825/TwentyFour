package com.onevgo.functions;

public class Levenshtein {
    public static int levenshtein(String str1, String str2) {
        return levenshtein(str1, str2, 1, 1, 1);
    }

    public static int levenshtein(String str1, String str2, int cosIns, int costRep, int costDel) {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }

        int len1 = str1.length();
        int len2 = str2.length();
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }

        if (len1 > len2) {
            String temStr = str1;
            str1 = str2;
            str2 = temStr;

            int tmpLen = len1;
            len1 = len2;
            len2 = tmpLen;

            int tmpCos = cosIns;
            cosIns = costDel;
            costDel = tmpCos;
        }

        int[] dp = new int[len1 + 1];

        // init...
        for (int i = 0; i < len1 + 1; i++) {
            dp[i] = i;
        }

        // j from 1 to len2+1
        for (int j = 1; j < len2 + 1; j++) {
            int prev = dp[0];
            char c2 = str2.charAt(j - 1);
            dp[0] = j;

            // i from 1 to len1+1
            for (int i = 1; i < len1 + 1; i++) {
                int curr = dp[i];
                char c1 = str1.charAt(i - 1);
                // dp[i - 1] + costDel: delete str2[j]
                // dp[i] + cosIns:      insert str2[j]
                dp[i] = Math.min(Math.min(dp[i - 1] + costDel, dp[i] + cosIns), prev + (c1 == c2 ? 0 : costRep));
                prev = curr;
            }
        }

        return dp[len1];
    }

    public static void main(String[] args) {
        String str1 = "carrrot1";
        String str2 = "carrots";
        System.out.println(levenshtein(str1, str2));
        System.out.println(levenshtein(str2, str1));
        System.out.println(levenshtein(str1, str2, 2, 3, 4));
        System.out.println(levenshtein(str2, str1, 2, 3, 4));
    }
}
