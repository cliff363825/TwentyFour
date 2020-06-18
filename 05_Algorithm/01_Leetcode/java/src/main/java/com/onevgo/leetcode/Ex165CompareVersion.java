package com.onevgo.leetcode;

public class Ex165CompareVersion {
    public int compareVersion(String version1, String version2) {
        int p1 = 0;
        int p2 = 0;

        while (p1 < version1.length() || p2 < version2.length()) {
            int cur1 = p1;
            int cur2 = p2;
            while (p1 < version1.length() && version1.charAt(p1) != '.') {
                p1++;
            }
            while (p2 < version2.length() && version2.charAt(p2) != '.') {
                p2++;
            }
            int v1 = 0, v2 = 0;
            if (cur1 != p1) {
                v1 = Integer.parseInt(version1.substring(cur1, p1));
            }
            if (cur2 != p2) {
                v2 = Integer.parseInt(version2.substring(cur2, p2));
            }
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }

            p1++;
            p2++;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Ex165CompareVersion().compareVersion("01", "1"));
    }
}
