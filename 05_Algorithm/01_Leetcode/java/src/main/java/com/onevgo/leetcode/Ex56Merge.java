package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Ex56Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[][]{};

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> lists = new ArrayList<>();

        int min = intervals[0][0];
        int max = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int curMin = intervals[i][0];
            int curMax = intervals[i][1];
            if (curMin > max || curMax < min) {
                lists.add(new int[]{min, max});
                min = curMin;
                max = curMax;
            } else {
                min = Math.min(min, curMin);
                max = Math.max(max, curMax);
            }
        }
        lists.add(new int[]{min, max});

        return lists.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18},
        };
        System.out.println(new Ex56Merge().merge(arr));
    }
}
