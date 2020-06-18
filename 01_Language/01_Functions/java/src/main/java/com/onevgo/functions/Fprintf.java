package com.onevgo.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Fprintf {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("test.txt"));
//            Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH) + 1;
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
            LocalDate now = LocalDate.now();
            int year = now.getYear();
            int month = now.getMonth().getValue();
            int day = now.getDayOfMonth();
            writer.printf("%04d-%02d-%02d", year, month, day);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
