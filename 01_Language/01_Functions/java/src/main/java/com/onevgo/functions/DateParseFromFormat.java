package com.onevgo.functions;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Locale;

public class DateParseFromFormat {
    public static void main(String[] args) {
        DateTime dateTime = DateUtil.parse("15-Feb-2009", "dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println(dateTime);
    }
}
