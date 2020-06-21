package sort;

import java.util.Arrays;

/**
 * 桶式排序
 */
public class BucketSort {
    public static void bucketSort(int[] arr, int min, int max) {
        int[] temp = new int[arr.length];
        int[] buckets = new int[max - min];
        for (int i = 0; i < arr.length; i++) {
            buckets[arr[i] - min]++;
        }
        System.out.println(Arrays.toString(buckets));
        for (int i = 1; i < max - min; i++) {
            buckets[i] = buckets[i] + buckets[i - 1];
        }
        System.out.println(Arrays.toString(buckets));
        System.arraycopy(arr, 0, temp, 0, arr.length);
        for (int k = arr.length - 1; k >= 0; k--) {
            arr[--buckets[temp[k] - min]] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 5, -1, 8, 5, 7, 3, -3, 1, 3};
        System.out.println("排序前：" + Arrays.toString(arr));
        bucketSort(arr, -3, 10);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
