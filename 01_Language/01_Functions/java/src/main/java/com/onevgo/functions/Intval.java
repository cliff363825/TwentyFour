package com.onevgo.functions;

import com.google.common.collect.Lists;

import java.util.Collection;

public class Intval {
    public static long intval(int var) {
        return intval(var, 10);
    }

    public static long intval(int var, int base) {
        return intval((long) var, base);
    }

    public static long intval(long var) {
        return intval(var, 10);
    }

    public static long intval(long var, int base) {
        return var;
    }

    public static long intval(float var) {
        return intval((double) var);
    }

    public static long intval(double var) {
        return (long) var;
    }

    public static long intval(String var) {
        return intval(var, 10);
    }

    public static long intval(String var, int base) {
        boolean negative = false;
        long minValue = -Long.MAX_VALUE; // -2147483647
        long mulValue = minValue / base; // -214748364

        long result = 0;
        for (int i = 0; i < var.length(); i++) {
            char c = var.charAt(i);
            if (i == 0) {
                if (c == '+') {
                    continue;
                } else if (c == '-') {
                    negative = true;
                    minValue = Long.MIN_VALUE; //  -2147483648
                    mulValue = minValue / base; // -214748364
                    continue;
                }
            }
            int digit = Character.digit(c, base);
            if (digit < 0) {
                break;
            }
            if (result < mulValue) {
                result = minValue;
                break;
            }
            result *= base;
            if (result < minValue + digit) {
                result = minValue;
                break;
            }
            result -= digit;
        }
        return negative ? result : -result;
    }

    public static long intval(Collection var) {
        return var == null ? 0 : (var.size() > 0 ? 1 : 0);
    }

    public static long intval(boolean var) {
        return var ? 1 : 0;
    }

    public static void main(String[] args) {
//        echo intval(42) . "\n";                      // 42
//        echo intval(4.2) . "\n";                     // 4
//        echo intval('42') . "\n";                    // 42
//        echo intval('+42') . "\n";                   // 42
//        echo intval('-42') . "\n";                   // -42
//        echo intval(042) . "\n";                     // 34
//        echo intval('042') . "\n";                   // 42
//        echo intval(1e10) . "\n";                    // 10000000000
//        echo intval('1e10') . "\n";                  // 10000000000
//        echo intval(0x1A) . "\n";                    // 26
//        echo intval(42000000) . "\n";                // 42000000
//        echo intval(420000000000000000000) . "\n";   // -4275113695319687168
//        echo intval('420000000000000000000') . "\n"; // 9223372036854775807
//        echo intval(42, 8) . "\n";                   // 42
//        echo intval('42', 8) . "\n";                 // 34
//        echo intval(array()) . "\n";                 // 0
//        echo intval(array('foo', 'bar')) . "\n";     // 1
//        echo intval(false) . "\n";                   // 0
//        echo intval(true) . "\n";                    // 1
        System.out.println(intval(42));
        System.out.println(intval(4.2));
        System.out.println(intval("42"));
        System.out.println(intval("+42"));
        System.out.println(intval("-42"));
        System.out.println(intval(042));
        System.out.println(intval("042"));
        System.out.println(intval(1e10));
        System.out.println(intval("1e10"));
        System.out.println(intval(0x1A));
        System.out.println(intval(42000000));
        //System.out.println(intval(420000000000000000000L));
        System.out.println(intval("420000000000000000000"));
        System.out.println(intval(42, 8));
        System.out.println(intval("42", 8));
        System.out.println(intval(Lists.newArrayList()));
        System.out.println(intval(Lists.asList("foo", "bar", new String[]{})));
        System.out.println(intval(false));
        System.out.println(intval(true));
    }
}
