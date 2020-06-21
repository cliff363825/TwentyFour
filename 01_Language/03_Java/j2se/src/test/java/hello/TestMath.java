package hello;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class TestMath {
    @Test
    public void testAbs() {
        int abs = Math.abs(-5);
        System.out.println("abs = " + abs);
    }

    @Test
    public void testSinCos() {
        double sin = Math.sin(30 * Math.PI / 180);
        System.out.println("sin = " + sin);

        double cos = Math.cos(Math.toRadians(60));
        System.out.println("cos = " + cos);

        double asin = Math.asin(0.5);
        System.out.println("asin = " + asin);
    }

    @Test
    public void testRandom() {
        for (int i = 0; i < 10; i++) {
            double random = Math.random(); // [0.0, 1.0)
            int v = (int) (random * 100); // [0, 100)
            System.out.println("v = " + v);
        }
    }

    @Test
    public void testMaxMin() {
        int max = Math.max(1, 2);
        System.out.println("max = " + max);

        int min = Math.min(1, 2);
        System.out.println("min = " + min);
    }

    @Test
    public void testRound() {
        long round = Math.round(12.345);
        System.out.println("round = " + round);
    }

    @Test
    public void testBigInteger() {
        BigInteger bigInteger = new BigInteger("12345678901234567890");
        BigInteger bigInteger1 = new BigInteger("23456789012345678901");

        BigInteger add = bigInteger.add(bigInteger1);
        System.out.println("add = " + add);

        BigInteger subtract = bigInteger.subtract(bigInteger1);
        System.out.println("subtract = " + subtract);

        BigInteger multiply = bigInteger.multiply(bigInteger1);
        System.out.println("multiply = " + multiply);

        BigInteger divide = bigInteger.divide(bigInteger1);
        System.out.println("divide = " + divide);

        BigInteger remainder = bigInteger.remainder(bigInteger1);
        System.out.println("remainder = " + remainder);
    }

    @Test
    public void testBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal("1234567890.0123456789");
        BigDecimal bigDecimal1 = new BigDecimal("2345678901.1098765432");

        BigDecimal add = bigDecimal.add(bigDecimal1);
        System.out.println("add = " + add);

        BigDecimal subtract = bigDecimal.subtract(bigDecimal1);
        System.out.println("subtract = " + subtract);

        BigDecimal multiply = bigDecimal.multiply(bigDecimal1);
        System.out.println("multiply = " + multiply);

        BigDecimal divide = bigDecimal.divide(bigDecimal1, RoundingMode.HALF_UP);
        System.out.println("divide = " + divide);

        BigDecimal remainder = bigDecimal.remainder(bigDecimal1);
        System.out.println("remainder = " + remainder);
    }

    @Test
    public void test1() {
        double money = 300000;
        double rate1 = 3.3 / 100 / 365;
        double rate2 = 3.4 / 100 / 365;

        double income1 = money * Math.pow(1 + rate1, 30);
        double income2 = money + money * rate2 * 30;
        System.out.println("income1 = " + income1);
        System.out.println("income2 = " + income2);
    }
}
