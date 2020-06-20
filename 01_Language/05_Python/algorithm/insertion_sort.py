# coding: utf-8

def insertion_sort(L=None):
    for j in range(1, len(L)):
        key = L[j]
        # 将 L[j] 插入到前面已经排序好的 L[0:j-1] 序列中
        i = j - 1
        # L[i] > L[j] => L[i+1] = L[i], i--
        # L[i] < L[j] => L[i+1] = L[j]
        while i >= 0 and L[i] > key:
            L[i + 1] = L[i]
            i = i - 1
        L[i + 1] = key
    return L


def insertion_sort_desc(L=None):
    for j in range(1, len(L)):
        key = L[j]
        # 将 L[j] 插入到前面已经排序好的 L[0:j-1] 序列中
        i = j - 1
        # L[i] < L[j] => L[i+1] = L[i], i--
        # L[i] > L[j] => L[i+1] = L[j]
        while i >= 0 and L[i] < key:
            L[i + 1] = L[i]
            i = i - 1
        L[i + 1] = key
    return L


def find_el(L, e):
    for i in range(len(L)):
        if L[i] == e:
            return i
    else:
        return None


if __name__ == "__main__":
    print(insertion_sort([5, 2, 4, 6, 1, 3]))
    print(insertion_sort([31, 41, 59, 26, 41, 58]))
    print(insertion_sort_desc([5, 2, 4, 6, 1, 3]))
    print(insertion_sort_desc([31, 41, 59, 26, 41, 58]))
    print(find_el([31, 41, 59, 26, 41, 58], 123))
