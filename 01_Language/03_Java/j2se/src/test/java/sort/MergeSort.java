package sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        // 归并排序
        sort(arr, 0, arr.length - 1);
    }

    // 将索引从left到right范围的数组元素进行归并排序
    private static void sort(int[] arr, int left, int right) {
        if (left < right) {
            // 找出中间索引
            int center = (left + right) / 2;
            sort(arr, left, center);
            sort(arr, center + 1, right);
            // 合并
            merge(arr, left, center, right);
        }
    }

    // 将两个数组进行归并，归并前两个数组已经有序，归并后依然有序
    private static void merge(int[] arr, int left, int center, int right) {
        int[] tempArr = new int[arr.length];
        int mid = center + 1;
        int third = left;
        int temp = left;
        while (left <= center && mid <= right) {
            if (arr[left] <= arr[mid]) {
                tempArr[third++] = arr[left++];
            } else {
                tempArr[third++] = arr[mid++];
            }
        }
        while (mid <= right) {
            tempArr[third++] = arr[mid++];
        }
        while (left <= center) {
            tempArr[third++] = arr[left++];
        }
        while (temp <= right) {
            arr[temp] = tempArr[temp++];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序前：" + Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
