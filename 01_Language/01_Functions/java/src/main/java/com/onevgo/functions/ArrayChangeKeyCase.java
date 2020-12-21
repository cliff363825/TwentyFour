package com.onevgo.functions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArrayChangeKeyCase {
    public static enum CaseType {
        CASE_LOWER, CASE_UPPER
    }

    public static <V> Map<String, V> arrayChangeKeyCase(Map<String, V> input) {
        return arrayChangeKeyCase(input, CaseType.CASE_LOWER);
    }

    public static <V> Map<String, V> arrayChangeKeyCase(Map<String, V> input, CaseType caseType) {
        return input.entrySet().stream().collect(Collectors.toMap(e -> {
            if (caseType == null || caseType == CaseType.CASE_LOWER) {
                return e.getKey().toLowerCase();
            } else {
                return e.getKey().toUpperCase();
            }
        }, Map.Entry::getValue, (o, o2) -> o2, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("FirSt", 1);
        map.put("FirST", 2);
        map.put("SecOnd", 4);
        System.out.println(arrayChangeKeyCase(map, CaseType.CASE_UPPER));
    }
}
