package sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void shellSort(int[] arr) {
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            System.out.println("===h的值： " + h + "===");
            for (int i = h; i < arr.length; i++) {
                int temp = arr[i];
                if (arr[i] < arr[i - h]) {
                    int j = i - h;
                    while (j >= 0 && arr[j] > temp) {
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    arr[j + h] = temp;
                }
            }
            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序前：" + Arrays.toString(arr));
        shellSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
