package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Ex119GetRow {
    public List<Integer> getRow(int rowIndex) {
        // first row
        List<Integer> prev = new ArrayList<>();
        prev.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> row = new ArrayList<>();
            // first num
            row.add(1);
            // nums...
            for (int j = 0; j < prev.size() - 1; j++) {
                row.add(prev.get(j) + prev.get(j + 1));
            }
            // last num
            row.add(1);
            prev = row;
        }

        return prev;
    }

    public static void main(String[] args) {

    }
}
