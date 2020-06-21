package sort;

import java.util.Arrays;

/**
 * 折半插入排序
 */
public class BinaryInsertSort {
    public static void binaryInsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp > arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            for (int j = i; j > low; j--) {
                arr[j] = arr[j - 1];
            }
            arr[low] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序前：" + Arrays.toString(arr));
        binaryInsertSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
