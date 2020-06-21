package sort;

import java.util.Arrays;

/**
 * 快速排序
 * 基本思想：
 * 任取待排序序列中的某个元素作为标准（也称为支点、界点，一般取第一个元素），
 * 通过一次划分，将待排元素分为左右两个子序列，左子序列元素的排序码均小于基准元素的排序码，
 * 右子序列的排序码则大于或等于基准元素的排序码，然后分别对两个子序列继续进行划分，
 * 直至每一个序列只有一个元素为止。
 */
public class QuickSort {
    public static void quickSort(int[] arr) {
        subSort(arr, 0, arr.length - 1);
    }

    /**
     * @param arr   待排序数组
     * @param start 数组开始索引值
     * @param end   数组结束索引值
     */
    private static void subSort(int[] arr, int start, int end) {
        if (start < end) {
            int base = arr[start];
            int i = start;
            int j = end + 1;
            while (true) {
                while (i < end && arr[++i] <= base) {
                    // 左侧索引右移
                }
                while (j > start && arr[--j] >= base) {
                    // 右侧索引左移
                }
                if (i < j) {
                    // 如果左侧索引值 小于 右侧索引值
                    // 说明左侧有值大于基准值，或者右侧有值小于基准值，将他们进行交换
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
            // 交换基准值所在位置（start <=> j）
            int temp = arr[j];
            arr[j] = base;
            arr[start] = temp;
            System.out.println(Arrays.toString(arr));
            subSort(arr, start, j - 1);
            subSort(arr, j + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序前：" + Arrays.toString(arr));
        quickSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
