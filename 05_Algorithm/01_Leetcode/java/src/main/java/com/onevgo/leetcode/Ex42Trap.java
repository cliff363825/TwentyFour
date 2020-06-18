package com.onevgo.leetcode;

public class Ex42Trap {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        //输入: [0,1,0,2,1,0,1,3,2,1,2,1]
        //输出: 6
        int[] leftHeight = new int[height.length];
        int[] rightHeight = new int[height.length];

        // 计算每个元素左侧的最大高度
        leftHeight[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftHeight[i] = Math.max(height[i], leftHeight[i - 1]);
        }
        // 计算每个元素右侧的最大高度
        rightHeight[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightHeight[i] = Math.max(height[i], rightHeight[i + 1]);
        }

        // 根据木桶效应，该元素的高度应该为左右侧的最小高度，Math.min(left, right)
        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            result += Math.min(leftHeight[i], rightHeight[i]) - height[i];
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new Ex42Trap().trap(new int[]{2, 0, 2}));
        System.out.println(new Ex42Trap().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
