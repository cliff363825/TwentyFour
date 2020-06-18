package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Ex118Generate {
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;

        // first row
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // first num
            row.add(1);
            // nums...
            List<Integer> prev = res.get(i - 1);
            for (int j = 0; j < prev.size() - 1; j++) {
                row.add(prev.get(j) + prev.get(j + 1));
            }
            // last num
            row.add(1);
            res.add(row);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
