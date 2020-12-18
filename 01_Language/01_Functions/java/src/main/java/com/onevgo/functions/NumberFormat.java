package com.onevgo.functions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class NumberFormat {
    public static String numberFormat(Number number) {
        return numberFormat(number, 0, '.', ',');
    }

    public static String numberFormat(Number number, int decimals, char decPoint, char thousandsSep) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(decPoint);
        decimalFormatSymbols.setGroupingSeparator(thousandsSep);

        DecimalFormat decimalFormat = new DecimalFormat("#,###.0");
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat.setMinimumFractionDigits(decimals);
        decimalFormat.setMaximumFractionDigits(decimals);

        return decimalFormat.format(number);
    }

    public static void main(String[] args) {
        System.out.println(numberFormat(1234.56));
        System.out.println(numberFormat(1234.56, 2, ',', ' '));
        System.out.println(numberFormat(1234.00, 2, ',', ' '));
    }
}
