package sort;

import java.util.Arrays;

/**
 * 堆排序
 * 1. 小顶堆： 父节点的节点值小于或等于左右子节点的值
 * 2. 大顶堆： 父节点的节点值大于或等于左右子节点的值
 */
public class HeapSort {
    // 对arr数组从0到lastIndex建大顶堆
    private static void buildMaxHeap(int[] arr, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // k保存当前正在判断的节点
            int k = i;
            // 如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                // k节点的左子节点的索引
                int biggerIndex = k * 2 + 1;
                // 如果biggerIndex小于lastIndex, 即biggerIndex + 1
                // 代表k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    // 如果右子节点的值较大
                    if (arr[biggerIndex] < arr[biggerIndex + 1]) {
                        // biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                // 如果k节点的值小于其较大子节点的值
                if (arr[k] < arr[biggerIndex]) {
                    // 交换他们
                    int temp = arr[k];
                    arr[k] = arr[biggerIndex];
                    arr[biggerIndex] = temp;
                    // 将biggerIndex赋给k, 开始while循环的下一次循环
                    // 重新保证k节点的值大于其左右节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    public static void heapSort(int[] arr) {
        // 循环建堆
        for (int i = 0; i < arr.length - 1; i++) {
            // 建堆
            buildMaxHeap(arr, arr.length - 1 - i);
            // 交换堆顶和最后一个元素
            int temp = arr[0];
            arr[0] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序前：" + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
