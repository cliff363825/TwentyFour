# coding: utf-8

"""
基本思想：
任取待排序序列中的某个元素作为标准（也称为支点、界点，一般取第一个元素），
通过一次划分，将待排元素分为左右两个子序列，左子序列元素的排序码均小于基准元素的排序码，
右子序列的排序码则大于或等于基准元素的排序码，然后分别对两个子序列继续进行划分，
直至每一个序列只有一个元素为止。
"""

def quicksort(l, start, end):
    """
    快速排序算法
    :type l: list 待排序的列表
    :type start: int 列表开始索引值
    :type end: int 列表结束索引值
    """
    if start < end:
        p = partition(l, start, end)
        quicksort(l, start, p-1)
        quicksort(l, p+1, end)

def partition(l, start, end):
    """
    计算基准值的索引值
    :type l: list 待排序的列表
    :type start: int 列表开始索引值
    :type end: int 列表结束索引值
    """
    base = l[start] # 选择开始元素为基准值
    i = start # 基准值的索引值
    for j in range(start+1, end+1): # l[j] from l[start+1] to l[end]
        if l[j] < base: # l[j] < l[start], 基准值索引位置右移，i++
            i += 1
            l[i], l[j] = l[j], l[i]
    if l[start] > l[i]:
        l[start], l[i] = l[i], l[start]
    print(l)
    return i # 返回基准值的索引值

l = [9, 7, 8, 0, 1, 2, 6, 5, 3, 4]
print(l)
quicksort(l, 0, len(l) - 1)
print(l)
