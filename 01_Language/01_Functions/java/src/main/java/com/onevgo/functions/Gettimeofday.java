package com.onevgo.functions;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class Gettimeofday {
    public static Map<String, Object> gettimeofday() {
        Instant now = Instant.now();

        Map<String, Object> result = new HashMap<>();
        result.put("sec", now.getEpochSecond());
        result.put("usec", now.getNano() / 1000);
        result.put("minuteswest", now.atZone(ZoneId.systemDefault()).getOffset().getTotalSeconds() / -60);
        //result.put("dsttime", 0);

        return result;
    }

    public static double gettimeofday(boolean returnFloat) {
        if (returnFloat) {
            return Instant.now().toEpochMilli() / 1000.0;
        }


        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        System.out.println(gettimeofday());
        System.out.println(gettimeofday(true));
    }
}
