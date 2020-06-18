package com.onevgo.functions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ArrayChangeKeyCase {
    public static enum CaseType {
        CASE_LOWER, CASE_UPPER
    }

    public static Map<String, Object> arrayChangeKeyCase(Map<String, Object> map) {
        return arrayChangeKeyCase(map, LinkedHashMap::new, CaseType.CASE_LOWER);
    }

    public static Map<String, Object> arrayChangeKeyCase(Map<String, Object> map, CaseType caseType) {
        return arrayChangeKeyCase(map, LinkedHashMap::new, caseType);
    }

    public static Map<String, Object> arrayChangeKeyCase(Map<String, Object> map, Supplier<Map<String, Object>> supplier) {
        return arrayChangeKeyCase(map, supplier, CaseType.CASE_LOWER);
    }

    public static Map<String, Object> arrayChangeKeyCase(Map<String, Object> map, Supplier<Map<String, Object>> supplier, CaseType caseType) {
        return map.entrySet().stream().collect(Collectors.toMap(e -> {
            if (caseType == null || caseType == CaseType.CASE_LOWER) {
                return e.getKey().toLowerCase();
            } else {
                return e.getKey().toUpperCase();
            }
        }, Map.Entry::getValue, (o, o2) -> o2, supplier));
    }

    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("FirSt", 1);
        map.put("FirST", 2);
        map.put("SecOnd", 4);
        System.out.println(arrayChangeKeyCase(map, LinkedHashMap::new, CaseType.CASE_UPPER));
    }
}
