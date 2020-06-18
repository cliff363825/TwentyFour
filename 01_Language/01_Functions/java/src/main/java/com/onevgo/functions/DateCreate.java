package com.onevgo.functions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateCreate {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
