# coding: utf-8
from math import floor


def merge(A, p, q, r):
    # 已知 A[p] - A[q] 和 A[q+1] - A[r] 是两个已经排序好的序列
    L = A[p:q + 1]  # type: list
    R = A[q + 1:r + 1]  # type: list
    L.append(2 ** 31 - 1)  # 令 L[q+1] 无穷大
    R.append(2 ** 31 - 1)  # 令 R[r+1] 无穷大

    i = 0
    j = 0
    for k in range(p, r + 1):
        if L[i] <= R[j]:
            # 左侧值小于等于右侧值 左侧值入列 索引值+1
            A[k] = L[i]
            i = i + 1
        else:
            # 左侧值大于右侧值 右侧值入列 索引值+1
            A[k] = R[j]
            j = j + 1


def merge_sort(A, p, r):
    # 将A[p] - A[r] 中的元素进行排序
    if p < r:
        q = floor((p + r) / 2)
        # 将A[p] - A[q] 中的元素进行排序
        merge_sort(A, p, q)
        # 将A[q+1] - A[r] 中的元素进行排序
        merge_sort(A, q+1, r)
        # A[p] - A[q] 和 A[q+1] - A[r] 是两个已经排序好的序列
        merge(A, p, q, r)


if __name__ == "__main__":
    A = [2, 4, 5, 7, 1, 2, 3, 6]
    print("begin merge", A)

    # merge(A, 0, 3, 7)
    merge_sort(A, 0, 7)

    print("after merge", A)
