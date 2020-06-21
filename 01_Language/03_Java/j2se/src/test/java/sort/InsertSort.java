package sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            if (arr[i] < arr[i - 1]) {
                // 右侧值 小于 左侧值
                int j = i - 1;
                while (j >= 0 && arr[j] > temp) {
                    // 比temp大的值右移
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序前：" + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
