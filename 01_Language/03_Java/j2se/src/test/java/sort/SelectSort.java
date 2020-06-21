package sort;

import java.util.Arrays;

/**
 * 直接选择排序
 * 直接选择排序的基本过程为：
 * 1. 在一组元素R[i]到R[n]中选择最小关键码的元素
 * 2. 若它不是这组元素中的第一个元素，则将它与这组元素中的第一个元素对调
 * 3. 除去具有最小关键字的元素，在剩下的元素中重复第(1)、(2)步，直到剩余的元素只有一个为止。
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
