package com.onevgo.leetcode;

import java.util.HashMap;

public class Ex166FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder sb = new StringBuilder();
        if (numerator > 0 ^ denominator > 0) {
            sb.append("-");
        }
        long dividend = Math.abs((long) numerator);
        long divisor = Math.abs((long) denominator);
        long n = dividend / divisor;
        long r = dividend % divisor;
        sb.append(n);
        if (r == 0) {
            return sb.toString();
        }
        sb.append(".");

        HashMap<Long, Integer> map = new HashMap<>();
        while (r != 0) {
            if (map.containsKey(r)) {
                int pos = map.get(r);
                sb.insert(pos, "(");
                sb.append(")");
                break;
            }
            map.put(r, sb.length());
            r *= 10;
            n = r / divisor;
            r = r % divisor;
            sb.append(n);
        }

        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
